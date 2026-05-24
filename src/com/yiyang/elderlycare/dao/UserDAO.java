package com.yiyang.elderlycare.dao;

import com.yiyang.elderlycare.entity.User;
import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
    User login(String username, String password);
    List<User> findByRoleId(Integer roleId);
    int add(User user);
    int update(User user);
    int delete(Integer id);
}
