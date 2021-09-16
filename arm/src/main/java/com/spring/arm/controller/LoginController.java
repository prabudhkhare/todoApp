package com.spring.arm.controller;

import com.spring.arm.model.login.UserSignUpRequest;
import com.spring.arm.service.LoginService;
import com.spring.arm.validator.SignupPayloadValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final SignupPayloadValidator signupPayloadValidator;
    private final LoginService loginService;

    @GetMapping("/sign-in")
    public String getSigninPage(@RequestParam(value = "signup",required = false) final String signup , Model model){
        if(signup!=null && signup.equals("success") ){
            model.addAttribute("isRedirectFromSignUp",true);
            LOGGER.info("signup successful");
        }
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignupPage(Model model){
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        model.addAttribute("user", userSignUpRequest);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpUser(@ModelAttribute("user") UserSignUpRequest userSignUpRequest, BindingResult result) throws Exception {
        signupPayloadValidator.validate(userSignUpRequest,result);
        if(result.hasErrors()){
            return "sign-up";
        }
        loginService.saveNewUserDetails(userSignUpRequest);
        return "redirect:sign-in?signup=success";
    }
}
