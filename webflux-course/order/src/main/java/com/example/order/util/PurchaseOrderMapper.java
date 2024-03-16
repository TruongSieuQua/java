package com.example.order.util;

import com.example.order.dto.PurchaseOrderResponseDto;
import com.example.order.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    @Mapping(target = "orderId", source = "id")
    PurchaseOrderResponseDto toPurchaseOrderResponseDto(PurchaseOrder purchaseOrder);

}
