package com.cinema.clientservice.payment;

public interface PaymentAPI {
    Long registerPayment(Long reservationId, double total);

    String getExternalUrlToRedirectTo(Long paymentId);
}
