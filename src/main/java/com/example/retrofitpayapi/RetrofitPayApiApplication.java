package com.example.retrofitpayapi;

import com.example.retrofitpayapi.common.config.properties.KakaoPayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({KakaoPayProperties.class})
public class RetrofitPayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrofitPayApiApplication.class, args);
	}

}
