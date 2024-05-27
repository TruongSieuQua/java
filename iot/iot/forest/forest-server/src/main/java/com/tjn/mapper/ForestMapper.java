package com.tjn.mapper;

import com.tjn.dto.ForestResponse;
import com.tjn.model.Forest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ForestMapper {

    ForestMapper INSTANCE = Mappers.getMapper(ForestMapper.class);

    ForestResponse toForestResponse(Forest forest);

}
