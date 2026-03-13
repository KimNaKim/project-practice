package com.camping.erp.domain.site;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiteServiceTest {

    @InjectMocks
    private SiteService siteService;

    @Mock
    private SiteRepository siteRepository;

    @Test
    @DisplayName("전체 사이트 목록 조회 테스트")
    void findAll_test() {
        // given
        Zone zone = Zone.builder()
                .id(1L)
                .name("A구역")
                .normalPrice(50000L)
                .peakPrice(80000L)
                .build();
        Site site1 = Site.builder()
                .id(1L)
                .siteName("A-1")
                .zone(zone)
                .maxPeople(4)
                .build();
        Site site2 = Site.builder()
                .id(2L)
                .siteName("A-2")
                .zone(zone)
                .maxPeople(4)
                .build();
        
        when(siteRepository.findAll()).thenReturn(List.of(site1, site2));

        // when
        List<SiteResponse.ListDTO> result = siteService.findAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getSiteName()).isEqualTo("A-1");
        assertThat(result.get(0).getZoneName()).isEqualTo("A구역");
        assertThat(result.get(0).getNormalPrice()).isEqualTo(50000L);
    }

    @Test
    @DisplayName("사이트 상세 조회 테스트")
    void findById_test() {
        // given
        Long siteId = 1L;
        Zone zone = Zone.builder()
                .id(1L)
                .name("A구역")
                .normalPrice(50000L)
                .peakPrice(80000L)
                .build();
        Site site = Site.builder()
                .id(siteId)
                .siteName("A-1")
                .zone(zone)
                .maxPeople(4)
                .build();
        
        when(siteRepository.findById(siteId)).thenReturn(Optional.of(site));

        // when
        SiteResponse.DetailDTO result = siteService.findById(siteId);

        // then
        assertThat(result.getSiteName()).isEqualTo("A-1");
        assertThat(result.getZoneName()).isEqualTo("A구역");
        assertThat(result.getMaxPeople()).isEqualTo(4);
        assertThat(result.getNormalPrice()).isEqualTo(50000L);
    }
}
