package com.nonglin.securitydemo01.controller;

import com.nonglin.securitydemo01.bean.User;
import com.nonglin.securitydemo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shen
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @PostFilter("filterObject.userId < 5")
    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        return userMapper.selectList(null);
    }

    @PreFilter("filterObject.length() > 3")
    @RequestMapping("/filterHoby")
    public List<String> filterHoby(@RequestParam List<String> hoby){
        return hoby;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello Spring Security";
    }

    @RequestMapping("/hi")
    public String sayHi() {
        return "hello Spring Security";
    }

    /**
     * 具备ROLE_ADMIN ROLE_USER权限的用户才能访问
     *
     */
    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @RequestMapping("/aa")
    public String saySecured() {
        return "hello @Secured";
    }

    /**
     * 具备ROLE_ADMIN ROLE_USER权限的用户才能访问
     * @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_MANAGER')")
     * @PreAuthorize("hasAuthority('admin')")
     */
    @PostAuthorize("hasAuthority('admin')")
    @RequestMapping("/bb")
    public String preAuth() {
        System.out.println("PostAuthorize");
        return "hello @Secured";
    }

    /**
     * 同时具备ROLE_ADMIN和ROLE_MANAGER角色的用户才能访问
     *
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_MANAGER')")
    @RequestMapping("/cc")
    public String preAuth2() {
        return "hello @Secured";
    }
}
