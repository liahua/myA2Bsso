package com.chenahua.jta.Service.impl;

import com.chenahua.jta.Service.HttpClientService;
import com.chenahua.jta.Service.UserService;
import com.chenahua.jta.entity.User;
import com.chenahua.jta.vo.ResultJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpClientService httpClientService;

    private ObjectMapper objectMapper=new ObjectMapper();



    @Override
    public boolean dologin(User user) throws IOException {
        String valueAsString = objectMapper.writeValueAsString(user);
        String url = "http://www.sso.com/user/doVerify";
        String result = httpClientService.doPost(url, valueAsString);
        ResultJson resultJson = objectMapper.readValue(result, ResultJson.class);
        int statu = resultJson.getData();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (statu == 200) {
            if (!isExistCookie("User_Token")) {
                Cookie userToken = new Cookie("User_Token", getToken());
                response.addCookie(userToken);
            }
            return true;
        }
        return false;
    }

    private String getToken() {
        String token = "JT_Ticket" + System.currentTimeMillis();
        token = DigestUtils.md5Digest(token.getBytes()).toString();
        return token;
    }

    private boolean isExistCookie(String userToken) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (name.equals(userToken)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }
}
