package com.spring.arm.model.security;

import com.spring.arm.jpa.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class ArmUserDetails implements UserDetails {
    private final User user;
    private final List<SimpleGrantedAuthority> authorityList;

    public String getName(){
        return this.user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        if(this.user == null){
            throw new IllegalArgumentException("User does not exist.");
        }
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        if(this.user == null){
            throw new IllegalArgumentException("User does not exist.");
        }
        return this.user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
