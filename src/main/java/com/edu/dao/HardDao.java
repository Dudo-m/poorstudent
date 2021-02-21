package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.entity.Hard;
import com.edu.vo.StudentAndHardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HardDao extends BaseMapper<Hard> {
    @Select("select t1.hard_id,t1.hard_reason,t1.hard_result,t2.* from hard t1 left join student t2 on t1.student_id= t2.student_id")
    IPage<StudentAndHardVO> pageHardVO(Page page);

    @Select("select t1.hard_id,t1.hard_reason,t1.hard_result,t2.* from hard t1 left join student t2 on t1.student_id= t2.student_id where hard_id = #{hardId}")
    StudentAndHardVO findStudentAndHardVOById(String hardId);
}
