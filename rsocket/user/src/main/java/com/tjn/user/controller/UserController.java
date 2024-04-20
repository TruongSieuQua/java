package com.tjn.user.controller;

import com.tjn.user.dto.OperationType;
import com.tjn.user.dto.UserDto;
import com.tjn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@MessageMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // Request stream
    @MessageMapping("get.all")
    public Flux<UserDto> allUser(){
        return this.userService.getAllUsers();
    }

    // Request response
    @MessageMapping("get.{id}")
    public Mono<UserDto> getUserById(@DestinationVariable String id){
        return this.userService.getUserById(id);
    }

    // Request response
    @MessageMapping("create")
    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono){
        return this.userService.createUser(userDtoMono);
    }

    // Request response
    @MessageMapping("update.{id}")
    public Mono<UserDto> updateUser(@DestinationVariable String id, Mono<UserDto> userDtoMono){
        return this.userService.updateUser(id, userDtoMono);
    }

    // Fire and forget
    @MessageMapping("delete.{id}")
    public Mono<Void> delete(@DestinationVariable String id){
        return this.userService.deleteUser(id);
    }

    @MessageMapping("operation.type")
    public Mono<Void> metadataOperationType(@Header("operation-type") OperationType operationType,
                                            Mono<UserDto> userDtoMono){
        System.out.println("metadataOperationType start");
        System.out.println(operationType);
        userDtoMono.doOnNext(System.out::println).subscribe();
        return Mono.empty();
    }
}
