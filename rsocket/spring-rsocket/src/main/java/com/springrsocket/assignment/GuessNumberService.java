package com.springrsocket.assignment;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GuessNumberService {

    public Flux<GuessNumberResponse> play(Flux<Integer> flux){
        // this will run once
        int serverNumber = ThreadLocalRandom.current().nextInt(1, 100);
        System.out.println("Server Number : " + serverNumber);

        // Phan phia tren co the cung cap noi dung cho flux nay
        // Tao cac operations cho flux request channel nguoi dung
        return flux.map(i -> this.compare(serverNumber, i));
    }

    private GuessNumberResponse compare(int serverNumber, int clientNumber){
        if(serverNumber > clientNumber)
            return GuessNumberResponse.GREATER;
        else if(serverNumber < clientNumber)
            return GuessNumberResponse.LESSER;
        return GuessNumberResponse.EQUAL;
    }

}
