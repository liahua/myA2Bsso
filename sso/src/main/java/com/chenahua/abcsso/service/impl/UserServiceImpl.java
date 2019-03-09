package com.chenahua.abcsso.service.impl;

import com.chenahua.abcsso.entity.User;
import com.chenahua.abcsso.mapper.SSOMapper;
import com.chenahua.abcsso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private com.chenahua.abcsso.mapper.SSOMapper SSOMapper;

    @Override
    public boolean verifyUserInfo(User user) {
        String password = user.getPassword();
        String username = user.getUsername();
        List<User> userbyUserNameAndPassWord = SSOMapper.findUserbyUserNameAndPassWord(username, password);
        if (!userbyUserNameAndPassWord.isEmpty()){
            return true;
        }
        return false;
    }
}
