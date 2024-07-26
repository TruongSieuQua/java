package com.example.product.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "products")
public class Product {
    @Id
    private String id;
    private String description;
    private Integer price;
}
