package com.example.order.util;

import com.example.order.dto.*;
import com.example.order.entity.PurchaseOrder;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContextMapper {

    ContextMapper INSTANCE = Mappers.getMapper(ContextMapper.class);

    @Mapping(source = "purchaseOrderRequestDto.userId", target = "userId")
    @Mapping(source = "productDto.price", target = "amount")
    TransactionRequestDto toTransactionRequestDto(RequestContext rc);

    @Named("transactionStatusToOrderStatus")
    @ValueMapping(source = "APPROVED", target = "COMPLETED")
    @ValueMapping(source = "DECLINED", target = "FAILED")
    @ValueMapping(source = MappingConstants.ANY_REMAINING, target = MappingConstants.THROW_EXCEPTION) //IllegalArgumentException
    OrderStatus toOrderStatus(TransactionStatus transactionStatus);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "purchaseOrderRequestDto.userId", target = "userId")
    @Mapping(source = "purchaseOrderRequestDto.productId", target = "productId")
    @Mapping(source = "productDto.price", target = "amount")
    @Mapping(source = "transactionResponseDto.status", target = "status", qualifiedByName = "transactionStatusToOrderStatus")
    PurchaseOrder toPurchaseOrder(RequestContext rc);

//    @Named("transactionRequestDto")
//    default  TransactionRequestDto toTransactionRequestDto(RequestContext rc){
//        TransactionRequestDto dto = new TransactionRequestDto();
//        dto.setUserId(rc.getPurchaseOrderRequestDto().getUserId());
//        dto.setAmount(rc.getProductDto().getPrice());
//        return dto;
//    }

//    default PurchaseOrder toPurchaseOrder(RequestContext rc){
//        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        purchaseOrder.setUserId(rc.getPurchaseOrderRequestDto().getUserId());
//        purchaseOrder.setProductId(rc.getPurchaseOrderRequestDto().getProductId());
//        purchaseOrder.setAmount(rc.getProductDto().getPrice());
//        TransactionStatus status = rc.getTransactionResponseDto().getStatus();
//        OrderStatus orderStatus = switch (status) {
//            case APPROVED -> OrderStatus.COMPLETED;
//            case DECLINED -> OrderStatus.FAILED;
//            default -> throw new IllegalArgumentException("Invalid order status: " + status);
//        };
//        purchaseOrder.setStatus(orderStatus);
//        return purchaseOrder;
//    }


}
