package com.camping.erp.domain.notice;

import com.camping.erp.domain.notice.enums.NoticeCategory;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

public class NoticeResponse {

    @Getter
    public static class ListDTO {
        private Long id;
        private String categoryDisplay;
        private String categoryClass;
        private String title;
        private String createdAt;
        private Long viewCount;

        public ListDTO(Notice notice) {
            this.id = notice.getId();
            this.categoryDisplay = notice.getCategory().getDisplay();
            this.categoryClass = getCategoryClass(notice.getCategory());
            this.title = notice.getTitle();
            this.createdAt = notice.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            this.viewCount = notice.getViewCount();
        }

        private String getCategoryClass(NoticeCategory category) {
            return switch (category) {
                case IMPORTANT -> "danger";
                case GENERAL -> "primary";
                case EVENT -> "info";
                case SYSTEM -> "secondary";
            };
        }
    }

    @Getter
    public static class DetailDTO {
        private Long id;
        private NoticeCategory category;
        private String categoryDisplay;
        private String title;
        private String content;
        private String createdAt;
        private Long viewCount;

        private boolean isImportant;
        private boolean isGeneral;
        private boolean isEvent;
        private boolean isSystem;

        public DetailDTO(Notice notice) {
            this.id = notice.getId();
            this.category = notice.getCategory();
            this.categoryDisplay = notice.getCategory().getDisplay();
            this.title = notice.getTitle();
            this.content = notice.getContent();
            this.createdAt = notice.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
            this.viewCount = notice.getViewCount();
            
            this.isImportant = notice.getCategory() == NoticeCategory.IMPORTANT;
            this.isGeneral = notice.getCategory() == NoticeCategory.GENERAL;
            this.isEvent = notice.getCategory() == NoticeCategory.EVENT;
            this.isSystem = notice.getCategory() == NoticeCategory.SYSTEM;
        }
    }
}
