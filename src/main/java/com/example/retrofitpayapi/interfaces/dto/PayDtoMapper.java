package com.example.retrofitpayapi.interfaces.dto;

import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PayDtoMapper {

    // INFO -> DTO

    PayDto.ReadyResponse toDto(PayInfo.ReadyInfo info);

    PayDto.ApprovalResponse toDto(PayInfo.ApprovalInfo info);
}
