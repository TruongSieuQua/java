package com.tjn.mapper;


import com.tjn.dto.SprinklerDto;
import com.tjn.model.Sprinkler;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SprinklerMapper {
    SprinklerMapper INSTANCE = Mappers.getMapper(SprinklerMapper.class);

    Sprinkler toSprinkler(SprinklerDto sprinklerDto);

    SprinklerDto toSprinklerDto(Sprinkler sprinkler);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateUserFromDto(SprinklerDto dto, @MappingTarget Sprinkler entity);

}
