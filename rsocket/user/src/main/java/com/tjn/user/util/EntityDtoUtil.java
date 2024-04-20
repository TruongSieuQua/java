package com.tjn.user.util;

import com.tjn.user.dto.TransactionRequest;
import com.tjn.user.dto.TransactionResponse;
import com.tjn.user.dto.UserDto;
import com.tjn.user.entity.User;
import com.tjn.user.dto.TransactionStatus;
import org.springframework.beans.BeanUtils;



public class EntityDtoUtil {
    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public static TransactionResponse toResponse(TransactionRequest request, TransactionStatus status){
        TransactionResponse response = new TransactionResponse();
        response.setAmount(request.getAmount());
        response.setType(request.getType());
        response.setUserId(request.getUserId());
        response.setStatus(status);
        return response;
    }
}
