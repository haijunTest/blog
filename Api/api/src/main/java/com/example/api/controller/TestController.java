package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/5/3, 9:49
 */
@Controller
@RequestMapping("/api")
public class TestController {

    @Autowired
    private FilterRegistrationBean filterRegistrationBean;

    @GetMapping("/getName")
    public Map<String, String> getName() {
        Map<String, String> initParameters = filterRegistrationBean.getInitParameters();
        return initParameters;
    }

    @GetMapping("/getSee")
    public String getSee (){
        return "/login";
    }
}
