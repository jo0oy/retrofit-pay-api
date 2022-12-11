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
@PropertySource("classpath:properties/naver.properties")
@ConfigurationProperties(prefix = "naver")
@Configuration
public class NaverPayProperties {

    @Value("${pay.baseUrl}")
    private String baseUrl;

    private Map<String, String> header = new HashMap<>();
}
