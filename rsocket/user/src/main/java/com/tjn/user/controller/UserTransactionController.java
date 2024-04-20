package com.tjn.user.controller;

import com.tjn.user.dto.OperationType;
import com.tjn.user.dto.TransactionRequest;
import com.tjn.user.dto.TransactionResponse;
import com.tjn.user.dto.UserDto;
import com.tjn.user.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@MessageMapping("user")
public class UserTransactionController {

    @Autowired
    private UserTransactionService transactionService;

    @MessageMapping("transaction")
    public Mono<TransactionResponse> doTransaction(Mono<TransactionRequest> req){
        return req.flatMap(this.transactionService::doTransaction);
    }
}
