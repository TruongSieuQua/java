package com.springrsocket;


import com.springrsocket.assignment.GuessNumberResponse;
import com.springrsocket.assignment.Player;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05AssignmentTest extends BaseTest {

    @RepeatedTest(3)
    public void assigment() {
        Player player = new Player();

        Mono<Void> mono =  this.requester.route("guess.a.number")
                // Data la 1 flux trong
                .data(player.guesses().delayElements(Duration.ofSeconds(1)))
                .retrieveFlux(GuessNumberResponse.class)
                .doFirst(player::play)
                // player.receives will continue to emit event to server
                .doOnNext(player.receives())
                // wait util complete or error signal
                .then();

        StepVerifier.create(mono)
                .verifyComplete();
    }
}
