package com.userregistration.service;

import com.userregistration.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User save(User user);
}
