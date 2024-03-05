package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("all")
    public Flux<ProductDto> all(){
        return productService.getAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(String id){
        return productService
                .getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(
            @RequestBody Mono<ProductDto> productDtoMono){
        return productService.insertProduct(productDtoMono);
    }

    @PostMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(
            @PathVariable String id,
            @RequestBody Mono<ProductDto> productDtoMono){
        return productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
