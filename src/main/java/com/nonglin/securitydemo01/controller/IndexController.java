package com.nonglin.securitydemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shen
 */
@Controller
public class IndexController {

    /**
     *  跳转登录页面
     */
    @RequestMapping(value = {"/", "/login.html"})
    public String toLogin(){
        return "login";
    }

    /**
     * 登录成功
     */
    @RequestMapping("/main.html")
    public String success(){
        return "main";
    }
}
