package com.camping.erp.domain.auth;

import com.camping.erp.domain.user.UserRequest;
import com.camping.erp.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login-form")
    public String loginForm() {
        return "auth/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "auth/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        userService.join(requestDTO);
        return "redirect:/login-form";
    }
}
