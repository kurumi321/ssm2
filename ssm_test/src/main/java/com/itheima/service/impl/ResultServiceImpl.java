package com.itheima.service.impl;

import com.itheima.domain.Result;
import com.itheima.domain.User;
import com.itheima.mapper.ResultMapper;
import com.itheima.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultMapper resultMapper;
    @Override
    public void save(Result result) {
        resultMapper.insertResult(result);
    }

    @Override
    public List<Result> findResultByuserId(User user) {
        return resultMapper.findResultByUserId(user.getId());
    }
}
