package com.camping.erp.domain.site;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SiteService {

    private final SiteRepository siteRepository;
    private final ZoneRepository zoneRepository;
    private final com.camping.erp.domain.reservation.ReservationRepository reservationRepository;

    public List<SiteResponse.ListDTO> findAll() {
        return siteRepository.findAllWithZone().stream()
                .map(SiteResponse.ListDTO::new)
                .collect(Collectors.toList());
    }

    public List<SiteResponse.ListDTO> findAvailableSites(java.time.LocalDate checkIn, java.time.LocalDate checkOut, Integer people) {
        List<Long> occupiedIds = reservationRepository.findOccupiedSiteIds(checkIn, checkOut);
        
        return siteRepository.findAllWithZone().stream()
                .filter(site -> !occupiedIds.contains(site.getId()))
                .filter(site -> people == null || site.getMaxPeople() >= people)
                .map(SiteResponse.ListDTO::new)
                .collect(Collectors.toList());
    }

    public List<Zone> findAllZones() {
        return zoneRepository.findAll();
    }

    public SiteResponse.DetailDTO findById(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 사이트가 존재하지 않습니다. id=" + id));
        return new SiteResponse.DetailDTO(site);
    }

    @Transactional
    public void saveZone(SiteRequest.ZoneSaveDTO requestDTO) {
        zoneRepository.save(requestDTO.toEntity());
    }

    @Transactional
    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }

    @Transactional
    public void saveSite(SiteRequest.SiteSaveDTO requestDTO) {
        Zone zone = zoneRepository.findById(requestDTO.getZoneId())
                .orElseThrow(() -> new RuntimeException("해당 구역이 존재하지 않습니다. id=" + requestDTO.getZoneId()));
        siteRepository.save(requestDTO.toEntity(zone));
    }

    @Transactional
    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
