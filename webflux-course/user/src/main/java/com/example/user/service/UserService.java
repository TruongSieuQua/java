package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.user.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public Flux<UserDto> all(){
        return userRepository.findAll()
                .map(userMapper::toUserDto);
    }

    public Mono<UserDto> getUserById(final int userId){
        return userRepository.findById(userId)
                .map(userMapper::toUserDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
        return userDtoMono
                .map(userMapper::toUser)
                .flatMap(userRepository::save)
                .map(userMapper::toUserDto);
    }

    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono){
        return userRepository.findById(id)
                .flatMap(u -> userDtoMono
                        .map(userMapper::toUser)
                .doOnNext(e -> e.setId(id)))
                .flatMap(userRepository::save)
                .map(userMapper::toUserDto);
    }

    public Mono<Void> deleteUser(int id){
        return userRepository.deleteById(id);
    }
}
