package com.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.dao.HardDao;
import com.edu.dao.StudentDao;
import com.edu.dao.SupportDao;
import com.edu.dao.UserDao;
import com.edu.entity.Student;
import com.edu.entity.User;
import com.edu.vo.DataVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService{

    @Resource
    UserDao userDao;
    @Resource
    StudentDao studentDao;
    @Resource
    SupportDao supportDao;
    @Resource
    HardDao hardDao;

    @Override
    public DataVO<User> pageUserBySearch(Integer currentPage, Integer pageSize, String userName) {
        DataVO<User> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userName!=null&&!userName.isEmpty()){
            queryWrapper.like("user_name", userName);
        }

        IPage<User> page = new Page<>(currentPage,pageSize);
        IPage<User> iPage = userDao.selectPage(page, queryWrapper);

        dataVO.setCount(iPage.getTotal());

        dataVO.setData(iPage.getRecords());
        return dataVO;
    }

    @Override
    @Transactional
    public int deleteUserById(String userId) {
        //删除用户如果是学生同时删除 困难申请，资助申请和学生信息
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        Student student = studentDao.selectOne(queryWrapper);
        if (student!=null){
            QueryWrapper query = new QueryWrapper();
            query.eq("student_id",student.getStudentId());
            //删除资助申请
            supportDao.delete(query);
            //删除困难申请
            hardDao.delete(query);
            //删除学生信息
            studentDao.deleteById(student.getStudentId());
        }
        return  userDao.deleteById(userId);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateById(user);
    }
}
