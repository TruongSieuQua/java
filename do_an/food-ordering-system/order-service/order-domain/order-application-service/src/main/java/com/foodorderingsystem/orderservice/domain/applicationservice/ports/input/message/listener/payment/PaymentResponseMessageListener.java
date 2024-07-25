package com.foodorderingsystem.orderservice.domain.applicationservice.ports.input.message.listener.payment;

import com.foodorderingsystem.orderservice.domain.applicationservice.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCanceled(PaymentResponse paymentResponse);
}
