package com.example.order.service;

import com.example.order.dto.PurchaseOrderResponseDto;
import com.example.order.repository.PurchaseOrderRepository;
import com.example.order.util.PurchaseOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final PurchaseOrderRepository orderRepository;

    private final PurchaseOrderMapper purchaseOrderMapper;

    public Flux<PurchaseOrderResponseDto> getProductByUserId(int userId){
        return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream()) // blocking by jpa
                .map(purchaseOrderMapper::toPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
