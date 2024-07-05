package com.tjn.controller;

import com.tjn.security.JWTUtil;
import com.tjn.security.payload.AuthRequest;
import com.tjn.security.payload.AuthResponse;
import com.tjn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationREST {

    private JWTUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        System.out.println(ar);
        return userService.findByUsername(ar.email())
                .filter(userDetails -> passwordEncoder.encode(ar.password()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
