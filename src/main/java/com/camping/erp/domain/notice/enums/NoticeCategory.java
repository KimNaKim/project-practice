package com.camping.erp.domain.notice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeCategory {
    IMPORTANT("중요"),
    GENERAL("일반"),
    EVENT("이벤트"),
    SYSTEM("시스템");

    private final String display;
}
