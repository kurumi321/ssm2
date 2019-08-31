package com.itheima.mapper;

import com.itheima.domain.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResultMapper {
    @Select("select * from result where userId = #{userId}")
    public List<Result> findResultByUserId(Integer userId);
    @Insert("insert into result values(#{id},#{userId},#{prize})")
    public void insertResult(Result result);
}
