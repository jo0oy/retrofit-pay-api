package com.example.retrofitpayapi.common.util.retrofit;

import com.example.retrofitpayapi.common.config.properties.KakaoPayProperties;
import com.example.retrofitpayapi.common.config.properties.NaverPayProperties;
import com.example.retrofitpayapi.infrastructures.order.pay.kakao.RetrofitKakaoPayApi;
import com.example.retrofitpayapi.infrastructures.order.pay.naver.RetrofitNaverPayApi;
import com.example.retrofitpayapi.infrastructures.order.pay.toss.RetrofitTossPayApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.UUID;

@Configuration
public class RetrofitServiceRegistry {

    @Bean
    public RetrofitKakaoPayApi retrofitKakaoPayApi(KakaoPayProperties kakaoPayProperties) {
        var baseUrl = kakaoPayProperties.getBaseUrl();
        var header = kakaoPayProperties.getHeader();

        var retrofit = RetrofitUtils.initRetrofit(baseUrl, header);

        return retrofit.create(RetrofitKakaoPayApi.class);
    }

    @Bean
    public RetrofitNaverPayApi retrofitNaverPayApi(NaverPayProperties naverPayProperties) {
        var partnerId = UUID.randomUUID().toString().substring(0, 8);

        var baseUrl = naverPayProperties.getBaseUrl() + partnerId + "/";
        var headers = naverPayProperties.getHeader();

        var retrofit = RetrofitUtils.initRetrofit(baseUrl, headers);

        return retrofit.create(RetrofitNaverPayApi.class);
    }

    @Bean
    public RetrofitTossPayApi retrofitTossPayApi() {
        var baseUrl = "https://pay.toss.im/";

        var retrofit = RetrofitUtils.initRetrofit(baseUrl, new HashMap<>());

        return retrofit.create(RetrofitTossPayApi.class);
    }
}
