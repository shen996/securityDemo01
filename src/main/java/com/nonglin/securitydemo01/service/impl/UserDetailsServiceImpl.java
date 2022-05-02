package com.nonglin.securitydemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nonglin.securitydemo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shen
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 使用条件查询
        QueryWrapper<com.nonglin.securitydemo01.bean.User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        com.nonglin.securitydemo01.bean.User user = userMapper.selectOne(wrapper);
        // 非空判断
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorities = null;
        // 将查询的结果封装成UserDetails
        if ("zhangsan".equals(user.getUsername())) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_MANAGER,ROLE_ADMIN");
        }
        if ("李四".equals(user.getUsername())) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        }
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),authorities);
    }
}
