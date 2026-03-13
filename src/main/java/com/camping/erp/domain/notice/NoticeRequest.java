package com.camping.erp.domain.notice;

import com.camping.erp.domain.notice.enums.NoticeCategory;
import lombok.Getter;
import lombok.Setter;

public class NoticeRequest {

    @Getter
    @Setter
    public static class SaveDTO {
        private NoticeCategory category;
        private String title;
        private String content;

        public Notice toEntity() {
            return Notice.builder()
                    .category(category)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class UpdateDTO {
        private NoticeCategory category;
        private String title;
        private String content;
    }
}
