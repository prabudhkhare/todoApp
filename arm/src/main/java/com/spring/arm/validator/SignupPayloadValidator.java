package com.spring.arm.validator;

import com.spring.arm.model.login.UserLoginRequest;
import com.spring.arm.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class SignupPayloadValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    private final LoginService loginService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserLoginRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserLoginRequest userLoginRequest = (UserLoginRequest)o;
        if(!validateEmail(userLoginRequest.getEmailId())){
            errors.rejectValue("emailId","1","email-id is not valid.");
        }
        if(loginService.checkIfIdAlreadyPresent(userLoginRequest.getEmailId())){
            errors.rejectValue("emailId","1","email-id is already registered.");
        }
        if(!StringUtils.hasText(userLoginRequest.getUsername())){
            errors.rejectValue("username","1","username can't be null.");
        }
        if(!StringUtils.hasText(userLoginRequest.getPassword())){
            errors.rejectValue("password","1","password can't be null.");
        }else if(userLoginRequest.getPassword().length() < 6){
            errors.rejectValue("password","1","password length can't be less than 6.");
        }
    }
    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
