package com.tjn.service;

import com.tjn.entity.AppUser;
import com.tjn.security.enumf.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Map<String, AppUser> data;

    @PostConstruct
    public void init() {
        data = new HashMap<>();
        data.put("user1@gmail.com", new AppUser("user1@gmail.com", "123456a@", true, List.of(Role.ROLE_USER)));
        data.put("admin1@gmail.com", new AppUser("admin1@gmail.com", "123456a@", true, List.of(Role.ROLE_ADMIN, Role.ROLE_USER)));
    }

    public Mono<AppUser> findByUsername(String username) {
        return Mono.justOrEmpty(data.get(username));
    }
}
