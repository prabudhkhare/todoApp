package com.spring.arm.controller;

import com.spring.arm.model.login.UserLoginRequest;
import com.spring.arm.model.security.ArmUserDetails;
import com.spring.arm.service.LoginService;
import com.spring.arm.util.JwtUtil;
import com.spring.arm.validator.SignInPayloadValidator;
import com.spring.arm.validator.SignupPayloadValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;


@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private static final String JWT_HEADER_STRING = "Authorization";
    private final SignupPayloadValidator signupPayloadValidator;
    private final SignInPayloadValidator signInPayloadValidator;
    private final LoginService loginService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    @GetMapping("/sign-in")
    public String getSignInPage(@RequestParam(value = "signup",required = false) final String signup , Model model, Authentication authentication ){
        if(authentication!=null){
            return "redirect:/index";
        }
        if(signup!=null && signup.equals("success") ){
            model.addAttribute("isRedirectFromSignUp",true);
            LOGGER.info("signup successful");
        }
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        model.addAttribute("user", userLoginRequest);
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model,Authentication authentication ){
        if(authentication!=null){
            return "redirect:/index";
        }
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        model.addAttribute("user", userLoginRequest);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpUser(@ModelAttribute("user") UserLoginRequest userLoginRequest, BindingResult result) throws Exception {
        signupPayloadValidator.validate(userLoginRequest,result);
        if(result.hasErrors()){
            return "sign-up";
        }
        loginService.saveNewUserDetails(userLoginRequest);
        return "redirect:sign-in?signup=success";
    }

    @PostMapping("/sign-in")
    public String signInUser(@ModelAttribute("user") UserLoginRequest userLoginRequest, BindingResult result, Model model, HttpServletResponse response) throws Exception {
        signInPayloadValidator.validate(userLoginRequest,result);
        if(result.hasErrors()){
            return "sign-in";
        }
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmailId(),
                            userLoginRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            model.addAttribute("signInFailure","Wrong credentials entered.");
            return "sign-in";
        }
        if(authenticate!= null && authenticate.isAuthenticated()) {
            ArmUserDetails userDetails=(ArmUserDetails)authenticate.getPrincipal();
            LOGGER.info("Successfully logged in with username = "+userDetails.getUsername()+" on -"+ LocalDateTime.now());
            String cookie=JWT_HEADER_STRING+"="+jwtTokenUtil.generateToken(userDetails.getUsername())+";";
            String cookieExpire =getCookieExpiryString(Long.parseLong(jwtTokenUtil.getEXPIRY()));
            response.setHeader("Set-Cookie",cookie+cookieExpire+"SameSite=Strict;Path=/;");
        }
        return "redirect:/index";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) throws Exception {
        String cookieExpire = getCookieExpiryString(0L);
        String cookie=JWT_HEADER_STRING+"=deleted;";
        response.setHeader("Set-Cookie",cookie+cookieExpire+"SameSite=Strict;Path=/;");
        return "redirect:/index";
    }

    private String getCookieExpiryString(Long expireAfterMilliSeconds){
        Date expdate= new Date();
        expdate.setTime(expdate.getTime()+expireAfterMilliSeconds);
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", java.util.Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return "expires=" + df.format(expdate)+";";
    }
}
