package com.h3w.webmodule.controller;

import com.h3w.webmodule.annotation.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class MainController {
    //TODO 调用service的全局对象,代码生成后取消这里的注释,并引入相关类
//    ServiceConfig service = ServiceConfig.serviceConfig;

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        //TODO 生成验证码代码生成后取消这里的注释
//        service.shiroCaptcha.generate(request, response);
    }

    @Log(operation = "访问首页")
    @GetMapping("/index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("message", "欢迎来到首页");
        return "/index";
    }

    @Log(operation = "访问登录页")
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equalsIgnoreCase("get")) {
            return "/login";
        } else {
            //TODO 验证验证码是否正确,代码生成后取消此处注释
//            service.shiroCaptcha.validate(request,response,request.getParameter("captcha"));
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
            usernamePasswordToken.setUsername("admin");
            usernamePasswordToken.setPassword("admin".toCharArray());
            usernamePasswordToken.setRememberMe(false);
            SecurityUtils.getSubject().login(usernamePasswordToken);
            if (SecurityUtils.getSubject().isAuthenticated())
                return "redirect:/index";
            return "redirect:/login";
        }
    }
}
