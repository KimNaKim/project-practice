package com.camping.erp.domain.site;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @GetMapping("/")
    public String index(Model model) {
        List<SiteResponse.ListDTO> sites = siteService.findAll();
        model.addAttribute("sites", sites);
        return "index";
    }

    @GetMapping("/sites/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        SiteResponse.DetailDTO site = siteService.findById(id);
        model.addAttribute("site", site);
        return "site/detail";
    }
}
