package com.nonglin.securitydemo01.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author shen
 */
@Data
public class User {

    @TableId
    private Integer userId;
    private String username;
    private String password;
}
