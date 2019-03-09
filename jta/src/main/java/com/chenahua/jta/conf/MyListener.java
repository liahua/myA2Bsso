package com.chenahua.jta.conf;

import com.chenahua.jta.mapper.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@Configuration
public class MyListener implements ServletRequestListener  {


    @Autowired
    private RedisMapper redisMapper;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("MyListener.requestInitialized");
    }
}
