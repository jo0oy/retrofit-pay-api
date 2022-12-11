package com.example.retrofitpayapi.interfaces.dto;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface OrderDtoMapper {

    OrderCommand.PaymentReq of(OrderDto.PaymentReq request);
}
