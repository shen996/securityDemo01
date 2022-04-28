package com.nonglin.securitydemo01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author shen
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加载userDetailsService实现类
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    /**
     * 自定义登录页面
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义登录页面
        http.formLogin()
                //登录页面设置
                .loginPage("/login.html")
                //登录访问路径 action
                .loginProcessingUrl("/login")
                //登录成功的跳转路径
                .defaultSuccessUrl("/main.html").permitAll()
            // 授权逻辑
            .and().authorizeRequests()
                //设置哪些页面可以直接访问
                .antMatchers("/", "/login.html").permitAll()
                //其他页面需要登录
                .anyRequest().authenticated()
            // 关闭csrf防护
            .and().csrf().disable();
    }

    /**
     * 返回解码器
     */
    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
