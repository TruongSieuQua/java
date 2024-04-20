package com.tjn.trading.controller;

import com.tjn.trading.client.StockClient;
import com.tjn.trading.dto.StockTickDto;
import com.tjn.trading.dto.StockTradeRequest;
import com.tjn.trading.dto.StockTradeResponse;
import com.tjn.trading.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("stock")
public class TradeController {

    // To push stock price to clients
    @Autowired
    private StockClient stockClient;

    // To trade stock
    @Autowired
    private TradeService tradeService;

    @GetMapping(value = "tick/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StockTickDto> stockTicks(){
        return this.stockClient.getStockStream();
    }

    @PostMapping("trade")
    public Mono<ResponseEntity<StockTradeResponse>> trade(@RequestBody Mono<StockTradeRequest> tradeRequestMono){
        return tradeRequestMono
                .filter(tr -> tr.getQuantity() > 0)
                .flatMap(this.tradeService::trade)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build()); // If quantity <= 0 => Bad request
    }

}
