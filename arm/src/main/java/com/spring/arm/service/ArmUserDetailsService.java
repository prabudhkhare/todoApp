package com.spring.arm.service;

import com.spring.arm.dao.UserDao;
import com.spring.arm.jpa.entity.User;
import com.spring.arm.model.security.ArmUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArmUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userDao.getUserById(s);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Username does not exist.");
        }
        User user1 = user.get();
        String authorities = user1.getAuthorities();
        return new ArmUserDetails(user1,getAuthoritiesList(authorities));
    }

    public static ArrayList<SimpleGrantedAuthority> getAuthoritiesList(String authorities){
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
        if(authorities!= null){
            String[] authoritiesArray =  authorities.split(",");
            for(String authority:authoritiesArray){
                SimpleGrantedAuthority auth  = new SimpleGrantedAuthority(authority);
                roles.add(auth);
            }
        }
        return roles;
    }
}
