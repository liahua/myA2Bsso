package com.chenahua.jta.Service;


import com.chenahua.jta.entity.User;

import java.io.IOException;

public interface UserService {
    boolean dologin(User user) throws IOException;
}
