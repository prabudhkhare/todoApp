package com.spring.arm.service;

import com.spring.arm.model.login.UserLoginRequest;

public interface LoginService {
    boolean checkIfEmailAlreadyPresent(String email);
    void saveNewUserDetails(UserLoginRequest userLoginRequest) throws Exception;
}
