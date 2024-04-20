package com.tjn.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.tjn.user.dto.TransactionStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private String userId;
    private int amount;
    private TransactionType type;
    private TransactionStatus status;

}
