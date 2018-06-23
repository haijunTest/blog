package com.example.api.controller;

import com.example.api.model.MmUser;
import com.example.api.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * class $classname
 *
 * @author haijun
 * @date 2018/4/19, 13:41
 */
@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/to/login")
    public String toLogin() {
        return "/login";
    }

    @ApiOperation(value = "登录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")}
    )
    @RequestMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String getLogin(String userName, String passWord,Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, passWord);
        Subject subject = SecurityUtils.getSubject();
        String auth = userInfoService.auth(userName, passWord);
        if ("success".equals(auth)) {
            try {
                //完成登录
                subject.login(usernamePasswordToken);
                MmUser user = (MmUser) subject.getPrincipal();
                model.addAttribute("user",user);
                return "/info";
            } catch (Exception e) {
                //返回登录页面
                return "/loginFail";
            }
        }
        return "/loginFail";
    }

    @RequestMapping(path = "/register")
    public String regist() {
        return "/register";
    }

    @RequestMapping(path = "/register/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String regist(String userName, String passWord, String passWordAgain) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = userInfoService.regist(userName, passWord, passWordAgain);
        if ("success".equals(result)) {
            return "/registerSuccess";
        }
        return "/registerFail";
    }

    @RequestMapping(path = "/item", method = RequestMethod.GET)
    public String getItem() {
        return "/item";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test (Model model){
        model.addAttribute("info","success---");
        return "/test";
    }

    @RequestMapping(value = "/user/info",method = RequestMethod.GET)
    public String getUserInfo (Model model){
        MmUser operator = userInfoService.getOperator();
        model.addAttribute("operator",operator);
        return "/info-edit";
    }
}
