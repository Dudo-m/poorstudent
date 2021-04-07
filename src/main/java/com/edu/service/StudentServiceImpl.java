package com.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.dao.HardDao;
import com.edu.dao.ResourceDao;
import com.edu.dao.StudentDao;
import com.edu.dao.SupportDao;
import com.edu.entity.Hard;
import com.edu.entity.Student;
import com.edu.entity.Support;
import com.edu.vo.ResourceApplyVO;
import com.edu.vo.ResourceAndSupportVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Resource
    StudentDao studentDao;
    @Resource
    ResourceDao resourceDao;
    @Resource
    SupportDao supportDao;
    @Resource
    HardDao hardDao;

    @Override
    public int SaveOrUpdateStudentInfo(Student student) {
        Student queryStudent = FindStudentByUserId(student.getUserId());
        if (queryStudent == null){
            return studentDao.insert(student);
        }else{
            student.setStudentId(queryStudent.getStudentId());
            return studentDao.updateById(student);
        }
    }

    @Override
    public Student FindStudentByUserId(String userId) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return studentDao.selectOne(queryWrapper);
    }

    @Override
    public IPage<ResourceApplyVO> ResourceListByPage(int currentPage,int PageSize,String studentId, String resourceName) {
        //查资源列表
        Page page = new Page<>(currentPage,PageSize);
        IPage<ResourceApplyVO> resourceApplyVOIPage = null;
        if (resourceName!=null&&!resourceName.isEmpty()){
           resourceApplyVOIPage = resourceDao.pageResourceApplyVOBySearch(page,resourceName);
        }else {
           resourceApplyVOIPage = resourceDao.pageResourceApplyVO(page);
        }
        //查已申请资源id
        QueryWrapper<Support> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        List<Support> supports = supportDao.selectList(queryWrapper);
        //匹配是否已申请过
        return matchApply(resourceApplyVOIPage,supports);
    }

    @Override
    public int SupportApplyAdd(String resourceId, String studentId) {
        //查看是否困难申请过
        QueryWrapper<Hard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        Hard hard = hardDao.selectOne(queryWrapper);
        if (hard==null||!hard.getHardResult().equals("通过")){
            return 0;
        }else {
            Support inSupport = new Support();
            inSupport.setStudentId(studentId);
            inSupport.setResourceId(resourceId);
            inSupport.setSupportResult("待认定");
            return supportDao.insert(inSupport);
        }
    }

    @Override
    public IPage<ResourceAndSupportVO> FindResourceVOByStudentIdPage(Integer currentPage, int PageSize, String studentId, String resourceName) {
        Page resourcePage = new Page<>(currentPage,PageSize);
        IPage<ResourceAndSupportVO> resourcesIPage;
        if (resourceName!=null&&!resourceName.isEmpty()){
            resourcesIPage = supportDao.pageResourceVOBySearch(resourcePage, studentId ,resourceName);
        }else {
            resourcesIPage = supportDao.pageResourceVO(resourcePage, studentId);
        }

        return resourcesIPage;
    }

    @Override
    public int SupportApplyDel(String supportId) {
        return supportDao.deleteById(supportId);
    }

    @Override
    public int SaveOrUpdateHard(Hard hard) {
        Hard queryHard = FindHardByStudentId(hard.getStudentId());
        if (queryHard == null){
            hard.setHardResult("待认定");
            return hardDao.insert(hard);
        }else{
            //mybatisplus更新时null值不更新
            hard.setHardId(queryHard.getHardId());
            return hardDao.updateById(hard);
        }
    }

    @Override
    public Hard FindHardByStudentId(String studentId) {
        QueryWrapper<Hard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id",studentId);
        return hardDao.selectOne(queryWrapper);
    }

    @Override
    public int HardApplyDel(String hardId) {
        return hardDao.deleteById(hardId);
    }

    //匹配是否已申请的方法
    private IPage<ResourceApplyVO> matchApply(IPage<ResourceApplyVO> resourceApplyVOIPage, List<Support> supports) {
        List<ResourceApplyVO> records = resourceApplyVOIPage.getRecords();
        for (int i = 0 ;i < records.size();i++){
            for (Support support:supports){
                if (records.get(i).getResourceId().equals(support.getResourceId())){
                    records.get(i).setFlag(true);
                }
            }
        }
        resourceApplyVOIPage.setRecords(records);
        return resourceApplyVOIPage;
    }
}
