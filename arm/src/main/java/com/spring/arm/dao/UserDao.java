package com.spring.arm.dao;

import com.spring.arm.jpa.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUserByEmail(String email);
    void saveUser(User user) throws Exception;
}
