package com.camping.erp.domain.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminReservationController {

    private final BookingService bookingService;

    @GetMapping("/reservations")
    public String reservationList(Model model) {
        List<ReservationResponse.DetailDTO> reservations = bookingService.findAll();
        model.addAttribute("reservations", reservations);
        return "admin/reservation/list";
    }

    @PostMapping("/reservations/{id}/confirm")
    public String confirm(@PathVariable("id") Long id) {
        bookingService.confirm(id);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/reservations/{id}/cancel")
    public String cancel(@PathVariable("id") Long id) {
        bookingService.cancel(id);
        return "redirect:/admin/reservations";
    }
}
