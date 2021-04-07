package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.entity.Resources;
import com.edu.vo.EcharsDataVO;
import com.edu.vo.ResourceApplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResourceDao extends BaseMapper<Resources> {
    @Select("select * from resource")
    IPage<ResourceApplyVO> pageResourceApplyVO(Page page);

    @Select("select * from resource where resource_name like CONCAT('%',#{resourceName},'%')")
    IPage<ResourceApplyVO> pageResourceApplyVOBySearch(Page page,String resourceName);

    @Select("select count(*) as value,resource_level as name from resource group by resource_level")
    List<EcharsDataVO> selectCountByResourceLevel();
}
