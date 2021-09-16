package com.spring.arm.service;

import com.spring.arm.dao.UserDao;
import com.spring.arm.jpa.entity.User;
import com.spring.arm.model.login.UserSignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkIfEmailAlreadyPresent(String email) throws Exception {
        return userDao.getUserByEmail(email).isPresent();
    }

    @Override
    public void saveNewUserDetails(UserSignUpRequest userSignUpRequest) throws Exception {
        User user = new User();
        user.markNew();
        user.setEmailId(userSignUpRequest.getEmailId());
        user.setUsername(userSignUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        user.setLastPasswordChanged(Date.from(Instant.now()));
        userDao.saveUser(user);
    }
}
