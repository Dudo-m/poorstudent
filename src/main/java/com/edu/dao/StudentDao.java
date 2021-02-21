package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {

}
