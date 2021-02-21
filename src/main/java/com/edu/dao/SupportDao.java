package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.entity.Support;
import com.edu.vo.ResourceAndSupportVO;
import com.edu.vo.ResourceStudentSupportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SupportDao extends BaseMapper<Support> {
    //左连接分页,根据学生id查申请的资源
    @Select("select t1.support_id,t2.*,t1.support_result from support t1 left join resource t2 on t1.resource_id = t2.resource_id where student_id = #{studentId}")
    IPage<ResourceAndSupportVO> pageResourceVO(Page page, String studentId);

    //多对多分页,查询学生申请列表
    @Select("select t1.support_id,t1.support_result,t2.*,t3.* from support t1 LEFT JOIN student t2 on t1.student_id=t2.student_id LEFT JOIN resource t3 on t1.resource_id=t3.resource_id")
    IPage<ResourceStudentSupportVO> pageSupportVO(Page page);

    @Select("select t1.support_id,t1.support_result,t2.*,t3.* from support t1 LEFT JOIN student t2 on t1.student_id=t2.student_id LEFT JOIN resource t3 on t1.resource_id=t3.resource_id where support_id = #{supportId}")
    ResourceStudentSupportVO selectSupportVOById(String supportId);
}
