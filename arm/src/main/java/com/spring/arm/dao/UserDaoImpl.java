package com.spring.arm.dao;

import com.spring.arm.jpa.entity.User;
import com.spring.arm.jpa.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDaoImpl implements UserDao{
    private final UserRepo userRepo;

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findById(email);
    }

    @Override
    public void saveUser(User user) throws Exception {
        userRepo.save(user);
    }
}
