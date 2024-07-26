package com.example.product.util;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //@Mapping(source = "name", target = "productName")
    Product toProduct(ProductDto productDto);

    //@Mapping(source = "productName", target = "name")
    ProductDto toProductDto(Product product);
}