package com.edu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.dao.UserDao;
import com.edu.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User finUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public int changePassword(User user) {
        //md5加盐加密
        String passwordSalt = user.getUserPassword()+"x@7faqgjw";
        String md5UserPassword = DigestUtils.md5DigestAsHex(passwordSalt.getBytes());
        user.setUserPassword(md5UserPassword);
        return userDao.updateById(user);
    }

    @Override
    public int register(User user) {
        //md5加盐加密
        String passwordSalt = user.getUserPassword()+"x@7faqgjw";
        String md5UserPassword = DigestUtils.md5DigestAsHex(passwordSalt.getBytes());
        user.setUserPassword(md5UserPassword);
        return userDao.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateById(user);
    }

}
