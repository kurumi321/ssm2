package com.itheima.service;

import com.itheima.domain.Result;
import com.itheima.domain.User;

import java.util.List;

public interface ResultService {
    public void save(Result result);

    public List<Result> findResultByuserId(User user);
}
