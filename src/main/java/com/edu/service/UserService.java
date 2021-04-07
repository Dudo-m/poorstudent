package com.edu.service;

import com.edu.entity.User;

public interface UserService {
    User finUserByUserName(String userName);

    int changePassword(User user);

    int register(User user);

    int updateUser(User user);
}
