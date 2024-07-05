package com.tjn.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class WebSecurityConfig {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        http
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .logout(ServerHttpSecurity.LogoutSpec::disable);

        http
                .exceptionHandling(exceptionHandlingSpec ->
                        exceptionHandlingSpec.authenticationEntryPoint((swe, e) ->
                                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                        ).accessDeniedHandler((swe, e) ->
                                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                        ))
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec.pathMatchers(HttpMethod.OPTIONS).permitAll()
                                .pathMatchers("/auth/**").permitAll()
                                .pathMatchers("/*/stream").permitAll()
                                .pathMatchers("/**").permitAll()
                                .anyExchange().permitAll())
        ;

        return http.build();
    }
}
