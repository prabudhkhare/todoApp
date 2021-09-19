package com.spring.arm.service;

import com.spring.arm.jpa.entity.User;
import com.spring.arm.model.login.UserLoginRequest;

import java.util.Optional;

public interface LoginService {
    boolean checkIfIdAlreadyPresent(String id);
    Optional<User> getUserById(String id);
    void saveNewUserDetails(UserLoginRequest userLoginRequest) throws Exception;
    void saveNewUser(User user) throws Exception;
    void updateUserDetails(User user) throws Exception;
}
