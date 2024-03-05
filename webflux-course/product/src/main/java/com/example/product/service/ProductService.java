package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.repository.ProductRepository;
import com.example.product.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public Flux<ProductDto> getAll(){
        return productRepository.findAll().map(productMapper::toProductDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return productRepository.findById(id).map(productMapper::toProductDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(productMapper::toProduct)
                .flatMap(productRepository::insert)
                .map(productMapper::toProductDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono){
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono
                        .map(productMapper::toProduct)
                        .doOnNext(e -> e.setId(p.getId()))
                ).flatMap(productRepository::save)
                .map(productMapper::toProductDto);
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
