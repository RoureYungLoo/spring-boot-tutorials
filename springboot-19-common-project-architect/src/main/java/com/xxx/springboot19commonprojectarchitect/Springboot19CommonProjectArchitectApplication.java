package com.xxx.springboot19commonprojectarchitect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.xxx.springboot19commonprojectarchitect.mapper"})
@SpringBootApplication
public class Springboot19CommonProjectArchitectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot19CommonProjectArchitectApplication.class, args);
    }

}
