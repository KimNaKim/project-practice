package com.camping.erp.domain.reservation;

import com.camping.erp.domain.reservation.enums.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationResponse {

    @Getter @Setter
    @Builder
    public static class ReserveDTO {
        private Long siteId;
        private String siteName;
        private String zoneName;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Integer people;
        private Long totalPrice;
        private Long stayDays;
    }

    @Getter @Setter
    public static class DetailDTO {
        private Long id;
        private String userName;
        private String siteName;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Long totalPrice;
        private ReservationStatus status;
        private LocalDateTime createdAt;
        
        private boolean isPending;
        private boolean isConfirmed;
        private boolean isCanceled;

        public DetailDTO(Reservation reservation) {
            this.id = reservation.getId();
            this.userName = reservation.getUser().getName();
            this.siteName = reservation.getSite().getSiteName();
            this.checkIn = reservation.getCheckIn();
            this.checkOut = reservation.getCheckOut();
            this.totalPrice = reservation.getTotalPrice();
            this.status = reservation.getStatus();
            this.createdAt = reservation.getCreatedAt();
            
            this.isPending = reservation.getStatus() == ReservationStatus.PENDING;
            this.isConfirmed = reservation.getStatus() == ReservationStatus.CONFIRMED;
            this.isCanceled = reservation.getStatus() == ReservationStatus.CANCEL_COMP;
        }
    }
}
