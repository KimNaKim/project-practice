package com.camping.erp.domain.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ReservationRequest {

    @Getter @Setter
    public static class ReserveDTO {
        private Long siteId;
        private LocalDate checkIn;
        private LocalDate checkOut;
        private Integer people;
    }
}
