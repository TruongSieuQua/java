package com.example.product.repository;

import com.example.product.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    // > min and < max
    Flux<Product> findByPriceBetween(int min, int max);

    // >= min and <= max
    Flux<Product> findByPriceBetween(Range<Integer> range);
}
