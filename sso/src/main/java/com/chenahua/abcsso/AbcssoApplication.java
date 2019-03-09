package com.chenahua.abcsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chenahua.abcsso.mapper")
public class AbcssoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbcssoApplication.class, args);
    }

}
