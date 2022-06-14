package com.dunshan.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGatewayApplication.class, args);
    }

}
//    private static final Logger logger = LoggerFactory.getLogger(OmsCartItemServiceImpl.class);
//    docker run --name sentinel --restart=always -p 8858:8858 -d bladex/sentinel-dashboard
