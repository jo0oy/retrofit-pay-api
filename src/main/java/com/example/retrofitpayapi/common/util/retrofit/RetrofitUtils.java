package com.example.retrofitpayapi.common.util.retrofit;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RetrofitUtils {

    private final static HttpLoggingInterceptor loggingInterceptor
            = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private final static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS);

    private final static Gson gson
            = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext)
                            -> LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),
                            DateTimeFormatter.ISO_LOCAL_DATE_TIME))
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setLenient()
            .create();

    public static Retrofit initRetrofit(String baseUrl, Map<String, String> headers) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.addInterceptor(chain -> {
                    var request = chain.request().newBuilder();

                    // header 주입
                    for (Map.Entry<String, String> entry: headers.entrySet()) {
                        log.info("{} : {}", entry.getKey(), entry.getValue());
                        request.addHeader(entry.getKey(), entry.getValue());
                    }
                    return chain.proceed(request.build());
                }).build())
                .build();
    }

    public <T> Optional<T> responseSync(Call<T> call) {
        try {
            Response<T> execute = call.execute();
            log.info("response execute = {}", execute);
            if (execute.isSuccessful()) {
                return Optional.ofNullable(execute.body());
            } else {
                log.error("requestSync errorBody = {}", execute.errorBody());
                throw new RuntimeException("retrofit execute response error");
            }
        } catch (IOException e) {
            log.error("", e);
            throw new RuntimeException("retrofit execute IOException");
        }
    }

    public void responseVoid(Call<Void> call) {
        try {
            if (!call.execute().isSuccessful()) throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
