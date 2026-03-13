package com.camping.erp.domain.site;

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
public class AdminSiteController {

    private final SiteService siteService;

    @GetMapping("/sites")
    public String siteList(Model model) {
        List<Zone> zones = siteService.findAllZones();
        List<SiteResponse.ListDTO> sites = siteService.findAll();
        model.addAttribute("zones", zones);
        model.addAttribute("sites", sites);
        return "admin/site/list";
    }

    @PostMapping("/zones/save")
    public String saveZone(SiteRequest.ZoneSaveDTO requestDTO) {
        siteService.saveZone(requestDTO);
        return "redirect:/admin/sites";
    }

    @PostMapping("/zones/delete/{id}")
    public String deleteZone(@PathVariable("id") Long id) {
        siteService.deleteZone(id);
        return "redirect:/admin/sites";
    }

    @PostMapping("/sites/save")
    public String saveSite(SiteRequest.SiteSaveDTO requestDTO) {
        siteService.saveSite(requestDTO);
        return "redirect:/admin/sites";
    }

    @PostMapping("/sites/delete/{id}")
    public String deleteSite(@PathVariable("id") Long id) {
        siteService.deleteSite(id);
        return "redirect:/admin/sites";
    }

    // 잘못된 GET 접근(직접 주소창 입력 등) 시 안전하게 목록으로 리다이렉트
    @GetMapping({"/zones/save", "/zones/delete/{id}", "/sites/save", "/sites/delete/{id}"})
    public String adminSiteFallback() {
        return "redirect:/admin/sites";
    }
}
