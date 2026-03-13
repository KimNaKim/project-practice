package com.camping.erp.domain.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/stat")
    public String stat() {
        return "admin/stat";
    }

    @GetMapping("/admin/qna")
    public String qnaList() {
        return "admin/qna/list";
    }

    @GetMapping("/admin/qna/{id}/answer")
    public String qnaAnswer(@PathVariable Long id) {
        return "admin/qna/answer";
    }

}
