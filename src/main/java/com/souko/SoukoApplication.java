package com.souko;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@MapperScan("com.souko.mapper")
public class SoukoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoukoApplication.class, args);
        log.info("启动成功");
    }

}
