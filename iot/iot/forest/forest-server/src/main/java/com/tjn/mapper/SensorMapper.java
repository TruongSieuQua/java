package com.tjn.mapper;

import com.tjn.dto.SensorDto;
import com.tjn.model.Sensor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorMapper INSTANCE = Mappers.getMapper(SensorMapper.class);

    Sensor toSensor(SensorDto sensorDto);

    SensorDto toSensorDto(Sensor sensor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Sensor updateSensor(SensorDto sensorDto, @MappingTarget Sensor sensor);

}
