package com.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.dao.HardDao;
import com.edu.dao.ResourceDao;
import com.edu.dao.StudentDao;
import com.edu.dao.SupportDao;
import com.edu.entity.Hard;
import com.edu.entity.Resources;
import com.edu.entity.Student;
import com.edu.entity.Support;
import com.edu.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Resource
    StudentDao studentDao;
    @Resource
    HardDao hardDao;
    @Resource
    ResourceDao resourceDao;
    @Resource
    SupportDao supportDao;

    @Transactional(readOnly = true)
    @Override
    public DataVO<Student> pageStudentBySearch(Integer currentPage,Integer pageSize,String studentName,String studentNumber) {
        DataVO<Student> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (studentName!=null&&!studentName.isEmpty()){
            queryWrapper.like("student_name", studentName);
        }
        if (studentNumber!=null&&!studentNumber.isEmpty()){
            queryWrapper.eq("student_number", studentNumber);
        }

        IPage<Student> page = new Page<>(currentPage,pageSize);
        IPage<Student> iPage = studentDao.selectPage(page, queryWrapper);

        dataVO.setCount(iPage.getTotal());

        dataVO.setData(iPage.getRecords());
        return dataVO;
    }

    @Override
    public IPage<StudentAndHardVO> studentAndHardVOPage(Integer currentPage, int PageSize) {
        Page page = new Page<>(currentPage,PageSize);
        return hardDao.pageHardVO(page);
    }

    @Override
    public int updateHardById(Hard hard) {
        return hardDao.updateById(hard);
    }

    @Override
    public StudentAndHardVO findStudentAndHardVOById(String hardId) {
        return hardDao.findStudentAndHardVOById(hardId);
    }

    @Override
    public Resources findResourceById(String resourceId) {
        return resourceDao.selectById(resourceId);
    }

    //学生申请资源列表
    @Override
    public IPage<ResourceStudentSupportVO> findSupportVOPage(Integer currentPage, int PageSize){
        Page page = new Page<>(currentPage,PageSize);
        return supportDao.pageSupportVO(page);
    }

    @Override
    public int updateSupportById(Support support) {
        return supportDao.updateById(support);
    }

    @Override
    public ResourceStudentSupportVO findResourceStudentSupportVOById(String supportId) {
        return supportDao.selectSupportVOById(supportId);
    }

    //echars
    @Override
    @Transactional(readOnly = true)
    public List<Integer> findSpecialList() {
        List<Integer> specialList = new ArrayList<>();
        EcharsDataVO2 echarsDataVO2 = studentDao.selectSpecialCount();
        //孤残,单亲，烈士子女，优抚对象，低保家庭
        specialList.add(echarsDataVO2.getOadCount());
        specialList.add(echarsDataVO2.getSpCount());
        specialList.add(echarsDataVO2.getComCount());
        specialList.add(echarsDataVO2.getFoCount());
        specialList.add(echarsDataVO2.getSaCount());
        return specialList;
    }

    //echars
    @Override
    @Transactional(readOnly = true)
    public List<EcharsDataVO> findEcharsDataList() {
        return resourceDao.selectCountByResourceLevel();
    }
}
