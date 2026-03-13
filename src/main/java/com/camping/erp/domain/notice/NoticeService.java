package com.camping.erp.domain.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<NoticeResponse.ListDTO> findAll() {
        return noticeRepository.findAll().stream()
                .map(NoticeResponse.ListDTO::new)
                .toList();
    }

    @Transactional
    public NoticeResponse.DetailDTO findById(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
        notice.incrementViewCount();
        return new NoticeResponse.DetailDTO(notice);
    }

    @Transactional
    public void save(NoticeRequest.SaveDTO requestDTO) {
        noticeRepository.save(requestDTO.toEntity());
    }

    @Transactional
    public void update(Long id, NoticeRequest.UpdateDTO requestDTO) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
        notice.update(requestDTO.getCategory(), requestDTO.getTitle(), requestDTO.getContent());
    }

    @Transactional
    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
