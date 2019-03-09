package com.chenahua.abcsso.mapper;

import com.chenahua.abcsso.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SSOMapper {
    @Select("select * from sso_user where username = #{username} and password = #{password}")
    List<User> findUserbyUserNameAndPassWord(@Param("username") String username, @Param("password") String password);
}
