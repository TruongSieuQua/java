package com.example.product.service;

import com.example.product.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
//        ProductDto p1 = new ProductDto("4k-tv", 2000);
//        ProductDto p2 = new ProductDto("slr-camera", 800);
//        ProductDto p3 = new ProductDto("iphone", 1000);
//        ProductDto p4 = new ProductDto("headphone", 200);
//        ProductDto p5 = new ProductDto("2k-monitor", 500);

        Flux.from(newProducts())
                .flatMap(p -> productService.insertProduct(Mono.just(p)))
                .subscribe(System.out::println);

    }

    private Flux<ProductDto> newProducts(){
        return Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(2))
                .map(i -> new ProductDto("product-" + i, ThreadLocalRandom.current().nextInt(10, 100)));
    }
}
