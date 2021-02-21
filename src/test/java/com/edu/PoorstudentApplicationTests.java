package com.edu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.dao.ResourceDao;
import com.edu.service.StudentService;

import com.edu.service.TeacherService;
import com.edu.vo.ResourceApplyVO;
import com.edu.vo.ResourceStudentSupportVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
class PoorstudentApplicationTests {
    @Resource
    TeacherService teacherService;
    @Test
    void contextLoads() {
        IPage<ResourceStudentSupportVO> supportVOPage = teacherService.findSupportVOPage(1,2);
        supportVOPage.getRecords().forEach(a->{
            System.out.println("a = " + a);
        });
    }
}
