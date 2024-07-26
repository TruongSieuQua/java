package com.example.order.service;

import com.example.order.client.ProductClient;
import com.example.order.client.UserClient;
import com.example.order.dto.PurchaseOrderRequestDto;
import com.example.order.dto.PurchaseOrderResponseDto;
import com.example.order.dto.RequestContext;
import com.example.order.repository.PurchaseOrderRepository;
import com.example.order.util.ContextMapper;
import com.example.order.util.PurchaseOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final ProductClient productClient;

    private final UserClient userClient;

    private final ContextMapper contextMapper;

    private final PurchaseOrderMapper purchaseOrderMapper;

    private final PurchaseOrderRepository purchaseOrderRepository;

    public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> requestDtoMono){
        //userId, productId
        return requestDtoMono.map(RequestContext::new)
                .flatMap(this::productRequestResponse) // get ProductDto from productId then set to context
                .doOnNext(rc -> {
                    rc.setTransactionRequestDto(contextMapper.toTransactionRequestDto(rc));
                }) // make transactionRequest = userId + amount of money = price of ProductDto
                .flatMap(this::userRequestResponse) // get TransactionResponse then set to context
                .map(contextMapper::toPurchaseOrder)
                .publishOn(Schedulers.boundedElastic())
                .map(this.purchaseOrderRepository::save) // blocking by using jpa
                .map(purchaseOrderMapper::toPurchaseOrderResponseDto);
                //.subscribeOn(Schedulers.boundedElastic()); // run on a separate scheduler because code is blocking
    }

    private Mono<RequestContext> productRequestResponse(RequestContext rc){
        return this.productClient.getProductById(rc.getPurchaseOrderRequestDto().getProductId())
                .doOnNext(rc::setProductDto)
                .retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(1)))
                .thenReturn(rc);
    }

    private Mono<RequestContext> userRequestResponse(RequestContext rc){
        return  this.userClient.authorizeTransaction(rc.getTransactionRequestDto())
                .doOnNext(rc::setTransactionResponseDto)
                .thenReturn(rc);
    }
}
