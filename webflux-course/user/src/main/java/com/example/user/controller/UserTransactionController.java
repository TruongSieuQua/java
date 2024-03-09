package com.example.user.controller;

import com.example.user.dto.TransactionRequestDto;
import com.example.user.dto.TransactionResponseDto;
import com.example.user.entity.UserTransaction;
import com.example.user.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
@RequiredArgsConstructor
public class UserTransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(
            @RequestBody Mono<TransactionRequestDto> requestDtoMono
    ){
        return requestDtoMono.flatMap(transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userid") int userId){
        return transactionService.getByUserId(userId);
    }

}
