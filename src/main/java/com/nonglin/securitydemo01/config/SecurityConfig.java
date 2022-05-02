package com.nonglin.securitydemo01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author shen
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;



    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        // 自动创建表，已创建的时候不需要该语句
//        tokenRepositoryImpl.setCreateTableOnStartup(true);
        return tokenRepositoryImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加载userDetailsService实现类
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    /**
     *  授权配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/403.html");
        // 退出登录
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html").permitAll();
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
                .antMatchers("/", "/login.html","/test/filterHoby","/main.html").permitAll()
                // 当前登录用户，有admin权限才可以访问 test/hi
                //.antMatchers("/test/hi").hasAuthority("admin")
                // 当前登录用户，有admin和user任意一个授权 可以访问test/hi
//                .antMatchers("/test/hi").hasAnyAuthority("admin", "user")
                // 当前用户 角色为ADMIN的可以访问
                .antMatchers("/test/hi").hasRole("USER")
                //其他页面需要登录
                .anyRequest().authenticated()
            .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .userDetailsService(userDetailsService);
            // 关闭csrf防护
//            .and().csrf().disable();
    }

    /**
     * 返回解码器
     */
    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
