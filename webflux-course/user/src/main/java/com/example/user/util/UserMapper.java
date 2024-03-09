package com.example.user.util;

import com.example.user.dto.UserDto;
import com.example.user.entity.User;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
