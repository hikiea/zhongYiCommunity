package com.example.homework.services;

import com.example.homework.Mapper.UserMapper;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserList {

    @Autowired
    private UserMapper userMapper;

    //使用UserInfoList对象把数据库中所有的数据用list承接起来
    public List<User> getUserList() {
        List<User> UserInfoList = userMapper.showAllData();
        return UserInfoList;
    }
}
