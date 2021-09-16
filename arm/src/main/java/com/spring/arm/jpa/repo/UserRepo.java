package com.spring.arm.jpa.repo;

import com.spring.arm.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,String> {
}
