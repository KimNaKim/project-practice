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

    public List<SiteResponse.ListDTO> findAll() {
        return siteRepository.findAll().stream()
                .map(SiteResponse.ListDTO::new)
                .collect(Collectors.toList());
    }

    public SiteResponse.DetailDTO findById(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 사이트가 존재하지 않습니다. id=" + id));
        return new SiteResponse.DetailDTO(site);
    }
}
