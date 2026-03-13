package com.camping.erp.domain.notice;

import com.camping.erp.domain.notice.enums.NoticeCategory;
import com.camping.erp.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notice_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticeCategory category;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Long viewCount;

    @Builder
    public Notice(Long id, NoticeCategory category, String title, String content, Long viewCount) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount != null ? viewCount : 0L;
    }

    public void update(NoticeCategory category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }
}
