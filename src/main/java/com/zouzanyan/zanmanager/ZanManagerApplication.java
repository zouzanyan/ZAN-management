package com.zouzanyan.zanmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zouzanyan.zanmanager.mapper")
public class ZanManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZanManagerApplication.class, args);
    }

}
