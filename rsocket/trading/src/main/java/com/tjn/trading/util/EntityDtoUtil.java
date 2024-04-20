package com.tjn.trading.util;

import com.tjn.trading.dto.*;
import com.tjn.trading.entity.UserStock;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static TransactionRequest toTransactionRequest(StockTradeRequest stockRequest, int amount){
        var transactionRequest = new TransactionRequest();
        switch (stockRequest.getTradeType()){
            case BUY -> transactionRequest.setType(TransactionType.DEBIT);
            case SELL -> transactionRequest.setType(TransactionType.CREDIT);
        }
        transactionRequest.setUserId(stockRequest.getUserId());
        // amount is the price
        transactionRequest.setAmount(amount);
        return transactionRequest;
    }

    public static StockTradeResponse toTradeResponse(StockTradeRequest request, TradeStatus status, int price){
        StockTradeResponse response = new StockTradeResponse();
        BeanUtils.copyProperties(request, response);
        response.setTradeStatus(status);
        response.setPrice(price);
        return response;
    }

    // not set quantity because it is plus, minus with userStock object not assign
    public static UserStock toUserStock(StockTradeRequest request){
        UserStock stock = new UserStock();
        stock.setUserId(request.getUserId());
        stock.setStockSymbol(request.getStockSymbol());
        stock.setQuantity(0);
        return stock;
    }

    public static UserStockDto toUserStockDto(UserStock userStock){
        UserStockDto dto = new UserStockDto();
        BeanUtils.copyProperties(userStock, dto);
        return dto;
    }

}
