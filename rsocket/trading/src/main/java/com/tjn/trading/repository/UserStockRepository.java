package com.tjn.trading.repository;

import com.tjn.trading.entity.UserStock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserStockRepository extends ReactiveMongoRepository<UserStock, String> {

    Mono<UserStock> findByUserIdAndStockSymbol(String userId, String stockSymbol);

    Flux<UserStock> findByUserId(String userId);
}
