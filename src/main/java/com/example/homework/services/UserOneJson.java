package com.example.homework.services;


import com.example.homework.Mapper.UserMapper;
import com.example.homework.dto.Result;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOneJson {

    @Autowired
    private UserMapper userMapper;

    public Result<List<User>> showOneJson(Integer id) {
        List<User> getOneJson = userMapper.id_to_findByALlData(id);
        Result result = new Result();
        result.setCode("200");
        result.setMsg("成功");
        result.setSuccess(true);
        result.setDetail(getOneJson);
        return result;
    }
}
