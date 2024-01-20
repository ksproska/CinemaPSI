package com.cinema.clientservice.payment.apis;

import com.cinema.clientservice.payment.PaymentAPI;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.math3.util.Precision.round;

public class MockPaymentAPI implements PaymentAPI {
    public final String externalServiceUrl;

    private final Map<Long, Double> registeredPayments = new HashMap<>(){};
    private Long counter = 0L;

    public MockPaymentAPI(String externalServiceUrl) {
        this.externalServiceUrl = externalServiceUrl;
    }

    @Override
    public Long registerPayment(Long reservationId, double total) {
        // registering payment in the external service
        if (total <= 0) throw new IllegalStateException("total must be a positive number");
        var paymentId = counter;
        counter += 1;
        registeredPayments.put(paymentId, round(total, 2));
        return paymentId;
    }

    @Override
    public String getExternalUrlToRedirectTo(Long paymentId) {
        // here would be implemented request to external service to acquire redirection endpoint
        return "redirect:" + externalServiceUrl + "?paymentId=" + paymentId + "&total=" + registeredPayments.get(paymentId);
    }
}
