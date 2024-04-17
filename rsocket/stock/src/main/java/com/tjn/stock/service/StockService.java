package com.tjn.stock.service;

import com.tjn.stock.dto.StockTickDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;


@Service
public class StockService {

    public static final Stock AMZN = new Stock(1000, "AMZN", 20);
    public static final Stock AAPL = new Stock(1000, "AAPL", 3);
    public static final Stock MSFT = new Stock(1000, "MSFT", 5);

    public Flux<StockTickDto> getStockPrice(){
        return Flux.interval(Duration.ofSeconds(2))
                .flatMap(i -> Flux.just(AMZN, AAPL, MSFT))
                .map(s -> {
                    s.updatePrice();
                    return new StockTickDto(s.getCode(), s.getPrice());
                });
    }
}
