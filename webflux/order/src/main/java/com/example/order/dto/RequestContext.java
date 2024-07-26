package com.example.order.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestContext {

    private PurchaseOrderRequestDto purchaseOrderRequestDto;

    private TransactionRequestDto transactionRequestDto;

    private TransactionResponseDto transactionResponseDto;

    private ProductDto productDto;

    public RequestContext(PurchaseOrderRequestDto purchaseOrderRequestDto) {
        this.purchaseOrderRequestDto = purchaseOrderRequestDto;
    }
}
