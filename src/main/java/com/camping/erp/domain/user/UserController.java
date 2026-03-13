package com.camping.erp.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 관리자: 회원 목록 조회
    @GetMapping("/admin/users")
    public String userList(Model model) {
        List<UserResponse.ListDTO> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/user/list";
    }

    // 관리자: 회원 권한 토글
    @PostMapping("/admin/users/{id}/update-role")
    public String updateRole(@PathVariable("id") Long id) {
        userService.updateRole(id);
        return "redirect:/admin/users";
    }

    // 마이페이지 홈
    @GetMapping("/mypage")
    public String home() {
        return "mypage/home";
    }

    // 예약 내역
    @GetMapping("/mypage/reservations")
    public String reservations() {
        return "mypage/reservations";
    }

    // 내 리뷰
    @GetMapping("/mypage/reviews")
    public String reviews() {
        return "mypage/reviews";
    }

    // 예약 변경
    @GetMapping("/mypage/reservations/{id}/change")
    public String reservationChange(@PathVariable("id") Long id) {
        return "mypage/reservation-change";
    }

    @GetMapping("/mypage/reservations/{id}/change-done")
    public String reservationChangeDone(@PathVariable("id") Long id) {
        return "mypage/reservation-change-done";
    }

    @GetMapping("/mypage/reservations/{id}/cancel")
    public String reservationCancel(@PathVariable("id") Long id) {
        return "mypage/reservation-cancel";
    }

    @GetMapping("/mypage/reservations/{id}/cancel-done")
    public String reservationCancelDone(@PathVariable("id") Long id) {
        return "mypage/reservation-cancel-done";
    }
}
