package com.example.user.service;

import com.example.user.dto.TransactionRequestDto;
import com.example.user.dto.TransactionResponseDto;
import com.example.user.dto.TransactionStatus;
import com.example.user.entity.UserTransaction;
import com.example.user.repository.UserRepository;
import com.example.user.repository.UserTransactionRepository;
import com.example.user.util.UserTransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;

    private final UserTransactionRepository transactionRepository;

    private final UserTransactionMapper transactionMapper;

    private final TransactionalOperator operator;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto){
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> transactionMapper.toEntity(requestDto))
                .flatMap(transactionRepository::save)
                .as(operator::transactional)
                .map(entity -> transactionMapper.toDto(entity, TransactionStatus.APPROVED))
                .defaultIfEmpty(transactionMapper.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getByUserId(int userId){
        return transactionRepository.findByUserId(userId);
    }

}
