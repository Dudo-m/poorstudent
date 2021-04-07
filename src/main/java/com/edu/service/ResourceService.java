package com.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.entity.Resources;

public interface ResourceService {

    IPage<Resources> findAllResourcePage(Integer currentPage, int PageSize,String resourceName);

    int updateResource(Resources resources);

    int addResource(Resources resources);

    int delResource(String resourceId);
}
