package com.camping.erp.domain.reservation;

import com.camping.erp.domain.site.SiteResponse;
import com.camping.erp.domain.site.SiteService;
import com.camping.erp.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final SiteService siteService;
    private final BookingService bookingService;

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
    public String payment(@RequestParam("siteId") Long siteId,
                          @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                          @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                          @RequestParam(value = "people", defaultValue = "2") Integer people,
                          Model model) {

        SiteResponse.DetailDTO site = siteService.findById(siteId);
        long stayDays = ChronoUnit.DAYS.between(checkIn, checkOut);
        long totalPrice = site.getNormalPrice() * stayDays;

        ReservationResponse.ReserveDTO responseDTO = ReservationResponse.ReserveDTO.builder()
                .siteId(siteId)
                .siteName(site.getSiteName())
                .zoneName(site.getZoneName())
                .checkIn(checkIn)
                .checkOut(checkOut)
                .people(people)
                .totalPrice(totalPrice)
                .stayDays(stayDays)
                .build();

        model.addAttribute("res", responseDTO);
        return "reservation/payment";
    }

    @PostMapping("/reservations/reserve")
    public String reserve(ReservationRequest.ReserveDTO requestDTO, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        bookingService.reserve(requestDTO, sessionUser);
        return "redirect:/reservations/complete";
    }

    @GetMapping("/reservations/complete")
    public String complete() {
        return "reservation/complete";
    }
}
