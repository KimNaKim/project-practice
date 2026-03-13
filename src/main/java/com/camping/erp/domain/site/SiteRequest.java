package com.camping.erp.domain.site;

import lombok.Data;

public class SiteRequest {

    @Data
    public static class ZoneSaveDTO {
        private String name;
        private Long normalPrice;
        private Long peakPrice;

        public Zone toEntity() {
            return Zone.builder()
                    .name(name)
                    .normalPrice(normalPrice)
                    .peakPrice(peakPrice)
                    .build();
        }
    }

    @Data
    public static class SiteSaveDTO {
        private Long zoneId;
        private String siteName;
        private Integer maxPeople;

        public Site toEntity(Zone zone) {
            return Site.builder()
                    .zone(zone)
                    .siteName(siteName)
                    .maxPeople(maxPeople)
                    .build();
        }
    }
}
