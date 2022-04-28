package com.nonglin.securitydemo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shen
 */
@SpringBootApplication
@MapperScan("com.nonglin.securitydemo01.mapper")
public class SecurityDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo01Application.class, args);
    }

}
