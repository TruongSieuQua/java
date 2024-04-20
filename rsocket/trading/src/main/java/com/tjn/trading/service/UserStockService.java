package com.tjn.trading.service;

import com.tjn.trading.dto.StockTradeRequest;
import com.tjn.trading.dto.UserStockDto;
import com.tjn.trading.entity.UserStock;
import com.tjn.trading.repository.UserStockRepository;
import com.tjn.trading.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.parser.Entity;

@Service
public class UserStockService {

    @Autowired
    private UserStockRepository stockRepository;

    // buy
    public Mono<UserStock> buyStock(StockTradeRequest request){
        return this.stockRepository.findByUserIdAndStockSymbol(request.getUserId(), request.getStockSymbol())
                .defaultIfEmpty(EntityDtoUtil.toUserStock(request))
                .doOnNext(us -> us.setQuantity(us.getQuantity() + request.getQuantity()))
                .flatMap(this.stockRepository::save);
    }

    // sell
    public Mono<UserStock> sellStock(StockTradeRequest request){
        return this.stockRepository.findByUserIdAndStockSymbol(request.getUserId(), request.getStockSymbol())
                .filter(us -> us.getQuantity() >= request.getQuantity())
                .doOnNext(us -> us.setQuantity(us.getQuantity() - request.getQuantity()))
                .flatMap(this.stockRepository::save);
    }

    public Flux<UserStockDto> getUserStocks(String userId){
        return this.stockRepository.findByUserId(userId)
                .map(EntityDtoUtil::toUserStockDto);
    }

}
