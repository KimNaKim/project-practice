package com.camping.erp.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.camping.erp.global.handler.ex.Exception400;
import com.camping.erp.global.handler.ex.Exception401;
import com.camping.erp.global.handler.ex.Exception403;
import com.camping.erp.global.handler.ex.Exception404;
import com.camping.erp.global.handler.ex.Exception500;
import com.camping.erp.global.util.Resp;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        String acceptHeader = request.getHeader("Accept");
        return "XMLHttpRequest".equals(header) || (acceptHeader != null && acceptHeader.contains("application/json"));
    }

    @ExceptionHandler(Exception400.class)
    public Object ex400(Exception400 e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
    }

    @ExceptionHandler(Exception401.class)
    public Object ex401(Exception401 e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        return String.format("""
                <script>
                    alert('%s');
                    location.href = '/login-form';
                </script>
                """, e.getMessage());
    }

    @ExceptionHandler(Exception403.class)
    public Object ex403(Exception403 e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.FORBIDDEN, e.getMessage());
        }
        return String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
    }

    @ExceptionHandler(Exception404.class)
    public Object ex404(Exception404 e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
    }

    @ExceptionHandler(Exception500.class)
    public Object ex500(Exception500 e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public Object exDataIntegrity(org.springframework.dao.DataIntegrityViolationException e, HttpServletRequest request) {
        String msg = "참조된 데이터(예: 예약 내역)가 있어 삭제할 수 없습니다. 관련 데이터를 먼저 확인해 주세요.";
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.CONFLICT, msg);
        }
        return String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, msg);
    }

    @ExceptionHandler(Exception.class)
    public Object exUnknown(Exception e, HttpServletRequest request) {
        if (isAjaxRequest(request)) {
            return Resp.fail(HttpStatus.INTERNAL_SERVER_ERROR, "관리자에게 문의하세요");
        }
        return String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, "예상치 못한 오류가 발생했습니다. 관리자에게 문의하세요.");
    }
}
