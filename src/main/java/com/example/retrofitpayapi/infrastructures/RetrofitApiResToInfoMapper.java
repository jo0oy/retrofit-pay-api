package com.example.retrofitpayapi.infrastructures;

import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.infrastructures.order.pay.kakao.KaKaoPayApiResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RetrofitApiResToInfoMapper {

    @Mapping(target = "nextRedirectMobileUrl", ignore = true)
    PayInfo.ReadyInfo of(KaKaoPayApiResponse.ReadyResponse response);

    @Mapping(source = "response.cardInfo.purchaseCorp", target = "cardCorpName", ignore = true)
    @Mapping(source = "response.cardInfo.installMonth", target = "installMonth", ignore = true)
    PayInfo.ApprovalInfo of(KaKaoPayApiResponse.ApprovalResponse response);
}
