package com.tjn.trading.service;

import com.tjn.trading.client.StockClient;
import com.tjn.trading.client.UserClient;
import com.tjn.trading.dto.*;
import com.tjn.trading.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TradeService {

    @Autowired
    private UserStockService userStockService;

    // To add or minus balance when sell or buy stock
    @Autowired
    private UserClient userClient;

    // To know current price of symbol stock
    @Autowired
    private StockClient stockClient;

    public Mono<StockTradeResponse> trade(StockTradeRequest tradeRequest){
        // Create transactionRequest from sell block of stocks and its prices
        var transactionRequest = EntityDtoUtil.toTransactionRequest(tradeRequest, this.estimatePrice(tradeRequest));

        Mono<StockTradeResponse> stockTradeResponseMono = null;
        switch (tradeRequest.getTradeType()){
            case BUY -> stockTradeResponseMono = buyStock(tradeRequest, transactionRequest);
            case SELL -> stockTradeResponseMono = sellStock(tradeRequest, transactionRequest);
        }
        return stockTradeResponseMono
                .defaultIfEmpty(EntityDtoUtil.toTradeResponse(tradeRequest, TradeStatus.FAILED, 0));
    }

    private Mono<StockTradeResponse> buyStock(StockTradeRequest tradeRequest, TransactionRequest transactionRequest){
        // client buy stock let's do transaction and if OK then add client stock
        return this.userClient.doTransaction(transactionRequest)
                .filter(tr -> TransactionStatus.COMPLETED.equals(tr.getStatus()))
                .flatMap(tr -> this.userStockService.buyStock(tradeRequest))
                .map(us -> EntityDtoUtil.toTradeResponse(tradeRequest, TradeStatus.COMPLETED, transactionRequest.getAmount()));
    }

    private Mono<StockTradeResponse> sellStock(StockTradeRequest tradeRequest, TransactionRequest transactionRequest){
        // customer sell stock just do transaction add money to client
        return this.userStockService.sellStock(tradeRequest)
                .flatMap(us -> this.userClient.doTransaction(transactionRequest))
                .map(tr -> EntityDtoUtil.toTradeResponse(tradeRequest, TradeStatus.COMPLETED, transactionRequest.getAmount()));
    }

    private int estimatePrice(StockTradeRequest request){
        return request.getQuantity() * this.stockClient.getCurrentStockPrice(request.getStockSymbol());
    }


}
