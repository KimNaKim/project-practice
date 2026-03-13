package com.camping.erp.domain.gallery;

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
public class AdminGalleryController {

    private final GalleryService galleryService;

    @GetMapping("/galleries")
    public String galleryList(Model model) {
        List<GalleryResponse.ListDTO> galleries = galleryService.findAll();
        model.addAttribute("galleries", galleries);
        return "admin/gallery/list";
    }

    @GetMapping("/galleries/new")
    public String newGalleryForm() {
        return "admin/gallery/new";
    }

    @PostMapping("/galleries/save")
    public String saveGallery(GalleryRequest.SaveDTO requestDTO) {
        galleryService.save(requestDTO);
        return "redirect:/admin/galleries";
    }

    @GetMapping("/galleries/edit/{id}")
    public String editGalleryForm(@PathVariable("id") Long id, Model model) {
        GalleryResponse.DetailDTO gallery = galleryService.findById(id);
        model.addAttribute("gallery", gallery);
        return "admin/gallery/new"; // Reusing new.mustache
    }

    @PostMapping("/galleries/delete/{id}")
    public String deleteGallery(@PathVariable("id") Long id) {
        galleryService.delete(id);
        return "redirect:/admin/galleries";
    }

    // Fallback for direct GET access to POST routes
    @GetMapping({"/galleries/save", "/galleries/delete/{id}"})
    public String adminGalleryFallback() {
        return "redirect:/admin/galleries";
    }
}
