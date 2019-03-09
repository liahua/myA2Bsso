package com.chenahua.jta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.chenahua.jta.mapper")
@SpringBootApplication()
public class JtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtaApplication.class, args);
    }

}
