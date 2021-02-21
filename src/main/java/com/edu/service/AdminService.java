package com.edu.service;

import com.edu.entity.User;
import com.edu.vo.DataVO;

public interface AdminService {

    DataVO<User> pageUserBySearch(Integer currentPage, Integer pageSize, String userName);

    int deleteUserById(String userId);

    int updateUser(User user);
}
