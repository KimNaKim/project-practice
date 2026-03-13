package com.camping.erp.domain.site;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SiteResponse {

    @Getter @Setter
    public static class ListDTO {
        private Long id;
        private String siteName;
        private String zoneName;
        private Long normalPrice;
        private Integer maxPeople;
        private String imageUrl; // 우선 목업 이미지 사용
        private Double rating;   // 우선 목업 데이터 사용 (4.0 ~ 5.0 사이)

        public ListDTO(Site site) {
            this.id = site.getId();
            this.siteName = site.getSiteName();
            this.zoneName = site.getZone().getName();
            this.normalPrice = site.getZone().getNormalPrice();
            this.maxPeople = site.getMaxPeople();
            this.imageUrl = "https://picsum.photos/seed/" + site.getId() + "/400/300"; // 목업
            this.rating = 4.5 + (site.getId() % 5) * 0.1; // 목업
        }
    }

    @Getter @Setter
    public static class DetailDTO {
        private Long id;
        private String siteName;
        private String zoneName;
        private String description;
        private Integer maxPeople;
        private Long normalPrice;
        private Long peakPrice;
        private List<String> imageUrls; // 우선 목업
        private List<String> amenities; // 우선 목업

        public DetailDTO(Site site) {
            this.id = site.getId();
            this.siteName = site.getSiteName();
            this.zoneName = site.getZone().getName();
            this.description = site.getZone().getName() + "에 위치한 쾌적한 사이트입니다.";
            this.maxPeople = site.getMaxPeople();
            this.normalPrice = site.getZone().getNormalPrice();
            this.peakPrice = site.getZone().getPeakPrice();
            this.imageUrls = List.of(
                "https://picsum.photos/seed/" + site.getId() + "1/800/600",
                "https://picsum.photos/seed/" + site.getId() + "2/800/600",
                "https://picsum.photos/seed/" + site.getId() + "3/800/600"
            );
            this.amenities = List.of("와이파이", "전기", "개별데크", "공용샤워실");
        }
    }
}
