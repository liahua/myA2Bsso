package com.chenahua.abcsso.controller;


import com.chenahua.abcsso.entity.User;
import com.chenahua.abcsso.mapper.RedisMapper;
import com.chenahua.abcsso.mapper.SSOMapper;
import com.chenahua.abcsso.service.UserService;

import com.chenahua.abcsso.vo.RedisCookie;
import com.chenahua.abcsso.vo.ResultJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @Autowired
    private RedisMapper redisMapper;


    private ObjectMapper objectMapper=new ObjectMapper();
    @Autowired
    private com.chenahua.abcsso.mapper.SSOMapper ssoMapper;


    @RequestMapping("/doVerify")
    public String doVerify(String redirect,String thisDomain,HttpServletRequest request,HttpServletResponse response){
        System.out.println("UserController.doVerify");
        Cookie[] cookies = request.getCookies();
        String loginURL = thisDomain + "/login";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sso_token")) {
                    String cookieValue = cookie.getValue();
                    System.out.println("UserController.doVerify"+cookieValue);
                    List<RedisCookie> tokenValue = redisMapper.findTokenValue(cookieValue);
                    System.out.println("UserController.doVerify"+tokenValue);
                    //根据cookie的token在数据库中找匹配的entry（token,userInfo）
                    if (!(tokenValue==null||tokenValue.isEmpty())){
                        RedisCookie redisCookie = tokenValue.get(0);
                        String userInfo = redisCookie.getUserInfo();
                        User user;
                        try {
                            user = objectMapper.readValue(userInfo, User.class);
                            System.out.println("UserController.doVerify"+user.getUsername());
                        }catch (Exception e ){

                            return "redirect:http://"+loginURL;
                        }
                        System.out.println("UserController.doVerify"+"   "+1);

                        if(user!=null){
                            StringBuilder setCookie = new StringBuilder(thisDomain).append("/setCookie?redirect=").append(redirect).append("&cookietoken=").append(cookieValue);
                            return "redirect:" +"http://"+ setCookie;
                        }
                    }
                }
            }
        }

        return "redirect:" + "http://"+loginURL;

    }

    /**
     * 登陸就是為了設置Cookie
     */
    @RequestMapping("/doLogin/{username}/{password}/{redirect}")
    public String dologin(@PathVariable String username, @PathVariable String password,HttpServletResponse response,@PathVariable String redirect) throws JsonProcessingException {
        List<User> userbyUserNameAndPassWord = ssoMapper.findUserbyUserNameAndPassWord(username, password);
        if (userbyUserNameAndPassWord!=null||(!(userbyUserNameAndPassWord.isEmpty()))){
            String cookietoken = setCookie(response);
            User user = userbyUserNameAndPassWord.get(0);
            String userJson = objectMapper.writeValueAsString(user);
            redisMapper.insertTokenValue(cookietoken,userJson);
            return "redirect:http://"+redirect+"/loginSuccess/"+cookietoken;
        }
        return "redirect:http://"+redirect+"/login.html";
    }

    private String setCookie(HttpServletResponse response) {
        String cookietoken =""+System.currentTimeMillis();
        Cookie token = new Cookie("sso_token",cookietoken);
        token.setPath("/");
        token.setMaxAge(3600*7*7);
        response.addCookie(token);
        return cookietoken;
    }


}
