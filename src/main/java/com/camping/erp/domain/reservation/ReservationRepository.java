package com.camping.erp.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r.site.id from Reservation r " +
           "where r.checkIn < :checkOut and r.checkOut > :checkIn " +
           "and r.status <> com.camping.erp.domain.reservation.enums.ReservationStatus.CANCEL_COMP")
    List<Long> findOccupiedSiteIds(@Param("checkIn") LocalDate checkIn, @Param("checkOut") LocalDate checkOut);
}
