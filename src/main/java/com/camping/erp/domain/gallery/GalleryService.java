package com.camping.erp.domain.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.camping.erp.domain.image.Image;
import com.camping.erp.domain.image.ImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GalleryService {
    private final GalleryRepository galleryRepository;
    private final ImageService imageService;

    public List<GalleryResponse.ListDTO> findAll() {
        return galleryRepository.findAllWithImages().stream()
                .map(GalleryResponse.ListDTO::new)
                .toList();
    }

    public GalleryResponse.DetailDTO findById(Long id) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("갤러리를 찾을 수 없습니다."));
        return new GalleryResponse.DetailDTO(gallery);
    }

    @Transactional
    public void save(GalleryRequest.SaveDTO requestDTO) {
        Gallery gallery = Gallery.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .build();
        
        List<Image> images = imageService.uploadImages(requestDTO.getImages());
        for (Image image : images) {
            gallery.addImage(image);
        }

        galleryRepository.save(gallery);
    }

    @Transactional
    public void delete(Long id) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("갤러리를 찾을 수 없습니다."));
        imageService.deleteImages(gallery.getImages());
        galleryRepository.delete(gallery);
    }
}
