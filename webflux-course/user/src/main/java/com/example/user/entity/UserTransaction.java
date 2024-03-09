package com.example.user.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@ToString
@Table(name = "transactions")
public class UserTransaction {
    @Id
    private Integer id;

    private Integer userId;

    private Integer amount;

    private LocalDateTime transactionDate;
}
