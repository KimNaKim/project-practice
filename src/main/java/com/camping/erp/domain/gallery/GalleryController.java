package com.camping.erp.domain.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/galleries")
    public String list(Model model) {
        List<GalleryResponse.ListDTO> galleries = galleryService.findAll();
        model.addAttribute("galleries", galleries);
        return "gallery/list";
    }
}
