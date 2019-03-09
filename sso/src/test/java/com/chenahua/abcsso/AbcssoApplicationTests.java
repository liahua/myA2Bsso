package com.chenahua.abcsso;

import com.chenahua.abcsso.entity.User;
import com.chenahua.abcsso.mapper.SSOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbcssoApplicationTests {

    @Autowired
    private com.chenahua.abcsso.mapper.SSOMapper SSOMapper;

    @Test
    public void contextLoads() {
        List<User> userbyUserNameAndPassWord = SSOMapper.findUserbyUserNameAndPassWord("1", "1");
        System.out.println(userbyUserNameAndPassWord);
    }

}
