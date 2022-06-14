package com.dunshan.mall.cart.component;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dunshan on 2019/9/5.
 */
@Configuration
public class FeignConfig {
    //超时时间
    public static int connectTimeOutMillis = 12000;
    public static int readTimeOutMillis = 12000;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    //    @Bean
//    public Retryer feignRetryer() {
//        return new Retryer.Default();
//    }
//    //自定义重试次数
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 4);
    }

}
