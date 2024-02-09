package com.example.selenium.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Television {
    @Value("${tv.name:sony}")
    private String name;

    public Television(){
        System.out.println("Inside the constructor: name =  " + this.name);
    }

    @PostConstruct
    public void init(){
        System.out.println("Inside @PostConstruct method: name = " + this.name);
        System.out.println("@PostConstruct ended TV will turn on!");
    }

    public void playMovie(){
        for (int i = 0; i < 3; i++) {
            System.out.println("Playing scense " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PreDestroy
    public void turnOff(){
        System.out.println("Inside @PreDestroy method: name = " + this.name);
        System.out.println("@PreDestroy ended TV will be turned off");
    }

}
