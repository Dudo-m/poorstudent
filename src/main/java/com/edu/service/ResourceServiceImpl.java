package com.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.dao.ResourceDao;
import com.edu.dao.SupportDao;
import com.edu.entity.Resources;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Resource
    ResourceDao resourceDao;

    @Override
    public IPage<Resources> findAllResourcePage(Integer currentPage, int PageSize,String resourceName) {
        IPage<Resources> page = new Page<>(currentPage,PageSize);
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        if (resourceName!=null&&!resourceName.isEmpty()){
            queryWrapper.like("resource_name",resourceName);
        }
        return resourceDao.selectPage(page,queryWrapper);
    }

    @Override
    public int delResource(String resourceId) {
        return resourceDao.deleteById(resourceId);
    }

    @Override
    public int updateResource(Resources resources) {
        return resourceDao.updateById(resources);
    }

    @Override
    public int addResource(Resources resources) {
        resources.setResourceTime(new Date());
        return resourceDao.insert(resources);
    }
}
