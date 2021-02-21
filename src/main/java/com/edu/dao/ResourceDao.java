package com.edu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.entity.Resources;
import com.edu.vo.ResourceApplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResourceDao extends BaseMapper<Resources> {
    @Select("select * from resource")
    IPage<ResourceApplyVO> pageResourceApplyVO(Page page);
}
