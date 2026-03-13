package com.camping.erp.domain.reservation;

import com.camping.erp.domain.site.SiteResponse;
import com.camping.erp.domain.site.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final SiteService siteService;

    @GetMapping("/reservations/new")
    public String newReservation(@RequestParam(value = "checkIn", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                 @RequestParam(value = "checkOut", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                                 @RequestParam(value = "people", required = false, defaultValue = "2") Integer people,
                                 Model model) {

        if (checkIn == null) checkIn = LocalDate.now();
        if (checkOut == null) checkOut = checkIn.plusDays(1);

        List<SiteResponse.ListDTO> sites = siteService.findAvailableSites(checkIn, checkOut, people);

        model.addAttribute("sites", sites);
        model.addAttribute("checkIn", checkIn);
        model.addAttribute("checkOut", checkOut);
        model.addAttribute("people", people);

        return "reservation/new";
    }

    @GetMapping("/reservations/payment")
    public String payment() {
        return "reservation/payment";
    }

    @GetMapping("/reservations/complete")
    public String complete() {
        return "reservation/complete";
    }
}
