package com.chenahua.jta.mapper;

import com.chenahua.jta.vo.RedisCookie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface RedisMapper {
    @Select("select * from redis_sso where tokenkey = #{tokenkey}")
    List<RedisCookie> findTokenValue(@Param("tokenkey") String token);

    @Insert("insert into redis_sso values (#{tokenkey},#{userInfo})")
    void insertTokenValue(@Param("tokenkey") String token, @Param("userInfo") String userInfo);
}
