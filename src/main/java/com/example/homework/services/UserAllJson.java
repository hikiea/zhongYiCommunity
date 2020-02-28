package com.example.homework.services;


import com.example.homework.Mapper.UserMapper;
import com.example.homework.dto.Result;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAllJson {

    @Autowired
    private UserMapper userMapper;

    public Result<List<User>> getAllJson() {
        //用getData对象获取数据库所有用户数据,用List承接数据
        List<User> getData = userMapper.showAllData();

        //新建一个result对象，把getData放进去，返回result对象
        Result<List<User>> result = new Result();
        result.setSuccess(true);
        result.setMsg("获取成功");
        result.setCode("200");
        result.setDetail(getData);
        return result;
    }
}
