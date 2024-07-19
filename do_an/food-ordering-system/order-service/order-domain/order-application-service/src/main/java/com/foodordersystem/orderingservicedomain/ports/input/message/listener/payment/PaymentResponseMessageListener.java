package com.foodordersystem.orderingservicedomain.ports.input.message.listener.payment;

import com.foodordersystem.orderingservicedomain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCanceled(PaymentResponse paymentResponse);
}
