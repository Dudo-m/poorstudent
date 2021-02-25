package com.edu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edu.dao.ResourceDao;
import com.edu.dao.StudentDao;
import com.edu.service.StudentService;

import com.edu.service.TeacherService;
import com.edu.vo.EcharsDataVO2;
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
    StudentDao studentDao;
    @Test
    void contextLoads() {
        EcharsDataVO2 echarsDataVO2 = studentDao.selectSpecialCount();
        System.out.println(echarsDataVO2.toString());
    }
}
