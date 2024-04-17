package com.springrsocket.security;

import com.springrsocket.dto.ComputationRequestDto;
import com.springrsocket.dto.ComputationResponseDto;
import com.springrsocket.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@MessageMapping("math.service.secured")
public class SecuredMathController {

    @Autowired
    private MathService service;

    @PreAuthorize("hasRole('USER')")
    @MessageMapping("square")
    public Mono<ComputationResponseDto> findSquare(Mono<ComputationRequestDto> requestDtoMono,
                                                   @AuthenticationPrincipal Mono<UserDetails> userDetailsMono){
        userDetailsMono.doOnNext(System.out::println).subscribe();

        return this.service.findSquare(requestDtoMono);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MessageMapping("table")
    public Flux<ComputationResponseDto> tableStream(Mono<ComputationRequestDto> requestDtoMono,
                                                    @AuthenticationPrincipal Mono<UserDetails> userDetailsMono){

        userDetailsMono.doOnNext(System.out::println).subscribe();

        return requestDtoMono.flatMapMany(this.service::tableStream);
    }

}
