package com.nonglin.securitydemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nonglin.securitydemo01.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author shen
 */
@Mapper
public interface UserMapper extends BaseMapper<User>  {
}
