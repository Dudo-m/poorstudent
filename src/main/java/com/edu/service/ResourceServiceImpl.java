package com.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.dao.ResourceDao;
import com.edu.dao.SupportDao;
import com.edu.entity.Resources;
import com.edu.entity.Support;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Resource
    ResourceDao resourceDao;
    @Resource
    SupportDao supportDao;

    @Override
    public IPage<Resources> findAllResourcePage(Integer currentPage, int PageSize) {
        IPage<Resources> page = new Page<>(currentPage,PageSize);
        return resourceDao.selectPage(page,null);
    }

    @Override
    @Transactional//开启事务
    public int delResource(String resourceId) {
        //同时删除关联的申请
        QueryWrapper<Support> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id",resourceId);
        int deleteSupport = supportDao.delete(queryWrapper);
        int deleteResource = resourceDao.deleteById(resourceId);
        return deleteSupport+deleteResource;
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
