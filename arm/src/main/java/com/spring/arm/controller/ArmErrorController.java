package com.spring.arm.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArmErrorController implements ErrorController {

    @RequestMapping("/error")
    public String getErrorPage(){
        return "error";
    }
}
