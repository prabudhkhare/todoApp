package com.spring.arm.service;

import com.spring.arm.dao.UserDao;
import com.spring.arm.jpa.entity.User;
import com.spring.arm.model.login.UserLoginRequest;
import com.spring.arm.model.security.Provider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean checkIfIdAlreadyPresent(String id) {
        return userDao.getUserById(id).isPresent();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public void saveNewUserDetails(UserLoginRequest userLoginRequest) throws Exception {
        User user = new User();
        user.markNew();
        user.setId(userLoginRequest.getEmailId());
        user.setEmailId(userLoginRequest.getEmailId());
        user.setUsername(userLoginRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userLoginRequest.getPassword()));
        user.setLastPasswordChanged(Date.from(Instant.now()));
        user.setLastLogin(Date.from(Instant.now()));
        user.setProvider(Provider.LOCAL);
        userDao.saveUser(user);
    }

    @Override
    public void saveNewUser(User user) throws Exception {
        user.markNew();
        userDao.saveUser(user);
    }

    @Override
    public void updateUserDetails(User user) throws Exception {
        userDao.saveUser(user);
    }
}
