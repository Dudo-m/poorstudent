package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
