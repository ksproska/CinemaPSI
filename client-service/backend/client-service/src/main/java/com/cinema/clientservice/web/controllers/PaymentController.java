package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.payment.PaymentAPI;
import com.cinema.clientservice.web.services.TicketService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class PaymentController {
    private final TicketService ticketService;

    private final Map<String, PaymentAPI> paymentServices;

    @Value("${frontend.payment.return.url.success}")
    private String successReturnUrl;

    @Value("${frontend.payment.return.url.failure}")
    private String failureReturnUrl;

    public PaymentController(TicketService ticketService, Map<String, PaymentAPI> paymentServices) {
        this.ticketService = ticketService;
        this.paymentServices = paymentServices;
    }

    @GetMapping("/pay")
    public String payment(@RequestParam("reservationId") Long reservationId, @RequestParam("paymentService") String paymentService) {
        PaymentAPI paymentAPI = Optional.ofNullable(paymentServices.get(paymentService)).orElseThrow();
        var total = ticketService.getTotal(reservationId);
        Long paymentId;
        try {
            paymentId = paymentAPI.registerPayment(reservationId, total);
        } catch (IllegalStateException e) {
            return "redirect:" + failureReturnUrl;
        }
        ticketService.savePaymentIdForReservation(reservationId, paymentId, paymentService);
        return paymentAPI.getExternalUrlToRedirectTo(paymentId);
    }

    @GetMapping(value = "pay/failure")
    public String cancelPay() {
        return "redirect:" + failureReturnUrl;
    }

    @GetMapping(value = "pay/success")
    public String successPay(@RequestParam("paymentId") Long paymentId) {
        try {
            ticketService.moveTicketReservationsToTickets(paymentId);
        } catch (IllegalStateException e) {
            return "redirect:" + failureReturnUrl;
        }
        return "redirect:" + successReturnUrl;
    }
}
