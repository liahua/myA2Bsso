package com.chenahua.jta.controller;

import com.chenahua.jta.Service.UserService;
import com.chenahua.jta.entity.User;
import com.chenahua.jta.intercepter.OrderIntercepter;
import org.apache.http.cookie.SetCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login.html";
    }


    @RequestMapping("/dologin/{username}/{password}")
    public String dologin(@PathVariable String username, @PathVariable String password, Model model) {

        System.out.println("username = " + username);
        System.out.println("password="+password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        String redirect= OrderIntercepter.THIS_DOMAIN;
        return "redirect:http://www.sso.com/doLogin/"+username+"/"+password+"/"+redirect;
    }


    @RequestMapping("/loginSuccess/{cookietoken}")
    public String dologin(@PathVariable String cookietoken, HttpServletResponse response) {
        setCookie(response,cookietoken);
        return "redirect:http://www.jta.com/index.html";
    }

    private String setCookie(HttpServletResponse response,String cookietoken) {
        Cookie token = new Cookie("jta_token",cookietoken);
        token.setPath("/");
        token.setMaxAge(3600*7*7);
        response.addCookie(token);
        return cookietoken;
    }

    @RequestMapping("/setCookie")
    public String setCookie(String redirect,HttpServletResponse response,String cookietoken){
        setCookie(response, cookietoken);
        return "redirect:http://www.jta.com"+redirect;
    }

}
