package com.example.retrofitpayapi.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Configuration
@PropertySource("classpath:properties/kakao.properties")
@ConfigurationProperties(prefix = "kakao")
public class KakaoPayProperties {

    @Value("${pay.baseUrl}")
    private String baseUrl;

    private Map<String, String> header = new HashMap<>();
}
