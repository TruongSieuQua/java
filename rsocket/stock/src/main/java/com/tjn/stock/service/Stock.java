package com.tjn.stock.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class Stock {

    @Getter
    private int price;

    @Getter
    private String code;

    private final int volatility;

    void updatePrice(){
        int random = ThreadLocalRandom.current().nextInt(-1*volatility, volatility+1);
        this.price += random;
        this.price = Math.max(this.price, 0);
    }
}
