package com.camping.erp.domain.reservation;

import com.camping.erp.domain.reservation.enums.ReservationStatus;
import com.camping.erp.domain.site.Site;
import com.camping.erp.domain.site.SiteRepository;
import com.camping.erp.domain.user.User;
import com.camping.erp.global.handler.ex.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {

    private final ReservationRepository reservationRepository;
    private final SiteRepository siteRepository;

    @Transactional
    public void reserve(ReservationRequest.ReserveDTO request, User sessionUser) {
        Site site = siteRepository.findById(request.getSiteId())
                .orElseThrow(() -> new Exception404("존재하지 않는 사이트입니다."));

        long stayDays = ChronoUnit.DAYS.between(request.getCheckIn(), request.getCheckOut());
        Long totalPrice = site.getZone().getNormalPrice() * stayDays;

        Reservation reservation = Reservation.builder()
                .user(sessionUser)
                .site(site)
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .totalPrice(totalPrice)
                .status(ReservationStatus.PENDING)
                .build();

        reservationRepository.save(reservation);
    }

    public List<ReservationResponse.DetailDTO> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponse.DetailDTO::new)
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .toList();
    }

    @Transactional
    public void confirm(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new Exception404("예약 내역을 찾을 수 없습니다."));
        reservation.updateStatus(ReservationStatus.CONFIRMED);
    }

    @Transactional
    public void cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new Exception404("예약 내역을 찾을 수 없습니다."));
        reservation.updateStatus(ReservationStatus.CANCEL_COMP);
    }
}
