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
import com.edu.vo.DataVO;
import com.edu.vo.EcharsDataVO;
import com.edu.vo.ResourceStudentSupportVO;
import com.edu.vo.StudentAndHardVO;
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
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        //孤残
        queryWrapper.eq("special_oad",true);
        specialList.add(studentDao.selectCount(queryWrapper));
        queryWrapper.clear();
        //单亲
        queryWrapper.eq("special_sp",true);
        specialList.add(studentDao.selectCount(queryWrapper));
        queryWrapper.clear();
        //烈士子女
        queryWrapper.eq("special_com",true);
        specialList.add(studentDao.selectCount(queryWrapper));
        queryWrapper.clear();
        //优抚对象
        queryWrapper.eq("special_fo",true);
        specialList.add(studentDao.selectCount(queryWrapper));
        queryWrapper.clear();
        //低保家庭
        queryWrapper.eq("special_sa",true);
        specialList.add(studentDao.selectCount(queryWrapper));
        queryWrapper.clear();
        return specialList;
    }

    //echars
    @Override
    @Transactional(readOnly = true)
    public List<EcharsDataVO> findEcharsDataList() {
        List<EcharsDataVO> echarsDataVOList = new ArrayList<>();
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        //院校类数量
        queryWrapper.eq("resource_level","院校");
        echarsDataVOList.add(new EcharsDataVO(resourceDao.selectCount(queryWrapper),"院校"));
        queryWrapper.clear();
        //社会类数量
        queryWrapper.eq("resource_level","社会");
        echarsDataVOList.add(new EcharsDataVO(resourceDao.selectCount(queryWrapper),"社会"));
        queryWrapper.clear();
        //国家类数量
        queryWrapper.eq("resource_level","国家");
        echarsDataVOList.add(new EcharsDataVO(resourceDao.selectCount(queryWrapper),"国家"));
        queryWrapper.clear();
        return echarsDataVOList;
    }
}
