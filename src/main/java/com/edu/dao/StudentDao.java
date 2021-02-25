package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.entity.Student;
import com.edu.vo.EcharsDataVO2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
    //echars查询特殊情况人数
    //-- select count(special_fo=1 OR NULL) from student
    @Select("SELECT sum( special_oad ) AS oadCount,sum( special_sp ) AS spCount,sum( special_com ) AS comCount,sum( special_fo ) AS foCount,sum( special_sa ) AS saCount FROM student")
    EcharsDataVO2 selectSpecialCount();
}
