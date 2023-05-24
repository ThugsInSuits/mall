package com.example.mall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.example.mall.coupon.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class MallCoupoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallCoupoonApplication.class, args);
    }

}
