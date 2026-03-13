package com.camping.erp.domain.gallery;

import com.camping.erp.domain.image.Image;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class GalleryResponse {

    @Getter
    public static class ListDTO {
        private Long id;
        private String title;
        private String createdAt;
        private String thumbnailPath;

        public ListDTO(Gallery gallery) {
            this.id = gallery.getId();
            this.title = gallery.getTitle();
            this.createdAt = gallery.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            this.thumbnailPath = (gallery.getImages() == null || gallery.getImages().isEmpty()) 
                ? "/css/images/no-image.png" 
                : gallery.getImages().get(0).getFilePath();
        }
    }

    @Getter
    public static class DetailDTO {
        private Long id;
        private String title;
        private String content;
        private String createdAt;
        private List<String> imagePaths;

        public DetailDTO(Gallery gallery) {
            this.id = gallery.getId();
            this.title = gallery.getTitle();
            this.content = gallery.getContent();
            this.createdAt = gallery.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
            this.imagePaths = gallery.getImages() == null 
                ? List.of() 
                : gallery.getImages().stream().map(Image::getFilePath).toList();
        }
    }
}
