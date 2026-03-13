package com.camping.erp.domain.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/notices")
    public String list(Model model) {
        List<NoticeResponse.ListDTO> notices = noticeService.findAll();
        model.addAttribute("notices", notices);
        return "notice/list";
    }

    @GetMapping("/notices/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        NoticeResponse.DetailDTO notice = noticeService.findById(id);
        model.addAttribute("notice", notice);
        return "notice/detail";
    }
}
