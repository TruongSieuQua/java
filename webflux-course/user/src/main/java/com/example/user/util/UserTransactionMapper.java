package com.example.user.util;

import com.example.user.dto.TransactionRequestDto;
import com.example.user.dto.TransactionResponseDto;
import com.example.user.dto.TransactionStatus;
import com.example.user.entity.UserTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Lazy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface UserTransactionMapper {
//    @Mapping(target = "transactionDate", expression = "java(LocalDateTime.now())")
//    UserTransaction toEntity(TransactionRequestDto transactionRequestDto);

    default UserTransaction toEntity(TransactionRequestDto transactionRequestDto) {
        if (transactionRequestDto == null) {
            return null;
        }
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setTransactionDate(LocalDateTime.now());
        userTransaction.setUserId(transactionRequestDto.getUserId());
        userTransaction.setAmount(transactionRequestDto.getAmount());
        return userTransaction;
    }

    TransactionResponseDto toDto(UserTransaction userTransaction, TransactionStatus status);

    TransactionResponseDto toDto(TransactionRequestDto transactionRequestDto, TransactionStatus status);
}
