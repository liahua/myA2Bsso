package com.chenahua.jta.intercepter;

import com.chenahua.jta.entity.User;
import com.chenahua.jta.mapper.RedisMapper;
import com.chenahua.jta.vo.RedisCookie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderIntercepter implements HandlerInterceptor {


    public static final String THIS_DOMAIN = "www.jta.com";
    public static final String SSO_DOMAIN = "www.sso.com";
    @Autowired
    private RedisMapper redisMapper;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jta_token")) {
                    String cookieValue = cookie.getValue();
                    List<RedisCookie> tokenValue = redisMapper.findTokenValue(cookieValue);
                    //根据cookie的token在数据库中找匹配的entry（token,userInfo）
                    if (!(tokenValue == null || tokenValue.isEmpty())) {
                        RedisCookie redisCookie = tokenValue.get(0);
                        String userInfo = redisCookie.getUserInfo();

                        User user = objectMapper.readValue(userInfo, User.class);

                        if (user != null) {
                            request.setAttribute("userObject", user);
                            return true;
                        }
                    }
                }
            }
        }
        //执行到这里，说明本域没有Cookie
        String requestURI = request.getRequestURI();
        StringBuilder location = new StringBuilder();
        location.append("http://").append(SSO_DOMAIN).append("/doVerify?").append("redirect=").append(requestURI).append("&thisDomain=").append(THIS_DOMAIN);
        response.sendRedirect(location.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
