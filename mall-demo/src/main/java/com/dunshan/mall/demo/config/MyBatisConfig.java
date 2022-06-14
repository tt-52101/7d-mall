package com.dunshan.mall.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 * Created by dunshan on 2019/4/8.
 */
@Configuration
@MapperScan("com.dunshan.mall.mapper")
public class MyBatisConfig {
}
