package com.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.entity.Hard;
import com.edu.entity.Student;
import com.edu.vo.ResourceApplyVO;
import com.edu.vo.ResourceAndSupportVO;

public interface StudentService {
    int SaveOrUpdateStudentInfo(Student student);

    Student FindStudentByUserId(String userId);

    IPage<ResourceApplyVO> ResourceListByPage(int currentPage, int PageSize,String studentId);

    int SupportApplyAdd(String resourceId,String studentId);

    IPage<ResourceAndSupportVO> FindResourceVOByStudentIdPage(Integer currentPage, int pageSize , String studentId);

    int SupportApplyDel(String supportId);

    int SaveOrUpdateHard(Hard hard);

    Hard FindHardByStudentId(String studentId);

    int HardApplyDel(String hardId);
}
