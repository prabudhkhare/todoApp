package com.spring.arm.validator;

import com.spring.arm.model.login.UserLoginRequest;
import com.spring.arm.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
@AllArgsConstructor
public class SignInPayloadValidator implements Validator {
    private final LoginService loginService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserLoginRequest.class.isAssignableFrom(aClass);
    }

    @SneakyThrows
    @Override
    public void validate(Object o, Errors errors) {
        UserLoginRequest userLoginRequest = (UserLoginRequest)o;
        if(!loginService.checkIfEmailAlreadyPresent(userLoginRequest.getEmailId())){
            errors.rejectValue("emailId","1","email-id is not registered.");
        }
    }
}
