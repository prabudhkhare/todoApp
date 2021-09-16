package com.spring.arm.service;

import com.spring.arm.model.login.UserSignUpRequest;

public interface LoginService {
    boolean checkIfEmailAlreadyPresent(String email) throws Exception;
    void saveNewUserDetails(UserSignUpRequest userSignUpRequest) throws Exception;
}
