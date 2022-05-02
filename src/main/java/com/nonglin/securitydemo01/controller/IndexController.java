package com.nonglin.securitydemo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shen
 */
@Controller
public class IndexController {

    @GetMapping("/update.html")
    public String toUpdate(){
        return "update";
    }

    @PostMapping("/update")
    public String update(){
        return "crsfTest";
    }

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

    /**
     *  跳转登录页面
     */
    @RequestMapping(value = {"/403.html"})
    public String toNoAuth(){
        return "error/403";
    }
}
