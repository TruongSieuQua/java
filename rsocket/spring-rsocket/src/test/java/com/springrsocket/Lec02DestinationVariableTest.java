package com.springrsocket;

import com.springrsocket.dto.ComputationRequestDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02DestinationVariableTest extends BaseTest {
    @Test
    public void fireAndForget(){
        Mono<Void> mono = requester.route("math.service.print.55").send();

        StepVerifier.create(mono)
                .verifyComplete();
    }
}
