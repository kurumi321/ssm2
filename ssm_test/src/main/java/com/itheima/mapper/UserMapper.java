package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where username = #{username} and password = #{password}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "resultList",column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.itheima.mapper.ResultMapper.findResultByUserId"))
    })
    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
