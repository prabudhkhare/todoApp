package com.spring.arm.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignUpRequest {
    private String emailId;
    private String username;
    private String password;

    public String getPassword() {
        if(this.password==null)
            return null;
        return new String(Base64.getDecoder().decode(this.password));
    }
}
