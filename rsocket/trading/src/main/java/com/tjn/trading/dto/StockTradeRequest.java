package com.tjn.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTradeRequest {

    private String userId;
    private String stockSymbol;
    private int quantity;
    private TradeType tradeType;

}
