package com.nonglin.securitydemo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author shen
 * 允许使用注解完成权限控制
 * 完成mapper的扫描
 */
@SpringBootApplication
@MapperScan("com.nonglin.securitydemo01.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo01Application.class, args);
    }

}
