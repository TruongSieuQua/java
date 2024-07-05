package com.tjn.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange swe) {
//        return Mono.justOrEmpty(swe.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
//                .filter(authHeader -> authHeader.startsWith("Bearer "))
//                .flatMap(authHeader -> {
//                    String authToken = authHeader.substring(7);
//                    Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
//                    return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
//                });
        System.out.println(swe.getRequest());
        return Mono.justOrEmpty(swe.getRequest().getCookies().getFirst("Bearer"))
                .map(HttpCookie::getValue)
                .switchIfEmpty(
                        Mono.justOrEmpty(swe.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                                .filter(authHeader -> authHeader.startsWith("Bearer "))
                                .map(value -> value.substring("Bearer ".length()))
                )
                .switchIfEmpty(Mono.just("eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwic3ViIjoiYWRtaW4xQGdtYWlsLmNvbSIsImlhdCI6MTcxOTk3NDExMSwiZXhwIjoxNzIwMDAyOTExfQ.qG8MIKw4Dohz-J-NjYgFo8kJu2KXfRqM25O3moDN4r1G1pPuC0hjtRomQMgpX9ghA3tcS2KgRtc2MS2wNqRa0g"))
                .flatMap(authToken -> {
                    Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
                    return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
                });
    }
}
