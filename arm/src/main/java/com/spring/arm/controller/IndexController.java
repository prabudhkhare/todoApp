package com.spring.arm.controller;

import com.spring.arm.model.security.ArmUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String getIndexPage(@RequestParam(value = "token",required = false) final String token, Model model , Authentication authentication){
        if(authentication!=null){
            ArmUserDetails user =(ArmUserDetails)authentication.getPrincipal();
            model.addAttribute("username",user.getName());
        }else{
            model.addAttribute("username","Anonymous");
        }
        return "index";
    }

}
