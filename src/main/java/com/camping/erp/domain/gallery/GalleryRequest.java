package com.camping.erp.domain.gallery;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class GalleryRequest {

    @Getter
    @Setter
    public static class SaveDTO {
        private String title;
        private String content;
        private List<MultipartFile> images;
    }
}
