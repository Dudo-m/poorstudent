package com.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.entity.Hard;
import com.edu.entity.Resources;
import com.edu.entity.Student;
import com.edu.entity.Support;
import com.edu.vo.DataVO;
import com.edu.vo.EcharsDataVO;
import com.edu.vo.ResourceStudentSupportVO;
import com.edu.vo.StudentAndHardVO;

import java.util.List;


public interface TeacherService {
    DataVO<Student> pageStudentBySearch(Integer currentPage,Integer pageSize,String studentName,String studentNumber);

    IPage<StudentAndHardVO> studentAndHardVOPage(Integer currentPage, int PageSize);

    int updateHardById(Hard hard);

    StudentAndHardVO findStudentAndHardVOById(String hardId);

    Resources findResourceById(String resourceId);

    List<Integer> findSpecialList();

    List<EcharsDataVO> findEcharsDataList();

    IPage<ResourceStudentSupportVO> findSupportVOPage(Integer currentPage, int PageSize);

    int updateSupportById(Support support);

    ResourceStudentSupportVO findResourceStudentSupportVOById(String supportId);
}
