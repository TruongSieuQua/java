package com.tjn.trading.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserStock {

    @Id
    private String id;
    private String userId;
    private String stockSymbol;
    private Integer quantity;
}
