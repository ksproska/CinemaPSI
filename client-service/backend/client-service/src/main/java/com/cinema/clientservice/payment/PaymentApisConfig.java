package com.cinema.clientservice.payment;

import com.cinema.clientservice.payment.apis.MockPaymentAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaymentApisConfig {
    @Bean
    Map<String, PaymentAPI> paymentServices(
            @Value("${external.payment.service.mock-service.name}") String externalServiceNameMockService,
            @Value("${external.payment.service.mock-service.url}") String externalServiceUrlMockService
    ) {
        var paymentServices = new HashMap<String, PaymentAPI>() {};
        paymentServices.put(externalServiceNameMockService, new MockPaymentAPI(externalServiceUrlMockService));
        return paymentServices;
    }
}
