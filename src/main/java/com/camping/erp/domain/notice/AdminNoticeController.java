package com.camping.erp.domain.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminNoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public String noticeList(Model model) {
        List<NoticeResponse.ListDTO> notices = noticeService.findAll();
        model.addAttribute("notices", notices);
        return "admin/notice/list";
    }

    @GetMapping("/notices/new")
    public String newNoticeForm() {
        return "admin/notice/new";
    }

    @PostMapping("/notices/save")
    public String saveNotice(NoticeRequest.SaveDTO requestDTO) {
        noticeService.save(requestDTO);
        return "redirect:/admin/notices";
    }

    @GetMapping("/notices/edit/{id}")
    public String editNoticeForm(@PathVariable("id") Long id, Model model) {
        NoticeResponse.DetailDTO notice = noticeService.findById(id);
        model.addAttribute("notice", notice);
        return "admin/notice/new"; // Reusing new.mustache for simplicity or create edit.mustache
    }

    @PostMapping("/notices/update/{id}")
    public String updateNotice(@PathVariable("id") Long id, NoticeRequest.UpdateDTO requestDTO) {
        noticeService.update(id, requestDTO);
        return "redirect:/admin/notices";
    }

    @PostMapping("/notices/delete/{id}")
    public String deleteNotice(@PathVariable("id") Long id) {
        noticeService.delete(id);
        return "redirect:/admin/notices";
    }

    // Fallback for direct GET access to POST routes
    @GetMapping({"/notices/save", "/notices/update/{id}", "/notices/delete/{id}"})
    public String adminNoticeFallback() {
        return "redirect:/admin/notices";
    }
}
