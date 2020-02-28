package com.example.homework.Controller;


import com.example.homework.Mapper.UserMapper;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class RegiestController {
    //实现自动装配
    @Autowired
    private UserMapper userMapper;

    //点击注册按钮后，获取input中的两个name，作为参数回传到regiest_show()，进行检测
    @PostMapping("/regiest")
    public String regiest_show(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            Model model
    ) {
        //把前端的username和password写入user对象，方便后续操作
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //使用UUID，生成唯一的随机数，写入数据库，作为唯一标识码
        String token = UUID.randomUUID().toString();
        user.setToken(token);

        if (username == null || username == "" || password == "" || password == null) {
            //注册失败，出错提示
            return "error/regisetError";
        } else if (password.length() < 6) {
            return "error/regisetError";
        } else {
            //注册成功
            //使用findByUsername（）方法，判断userCheck是否为空（在数据库中是否存在）
            User userCheck = userMapper.findByUsername(username);
            if (userCheck == null) {
                //账号还不存在数据库，执行插入操作，注册成功，返回ok_regiest页面
                userMapper.add(user);
                model.addAttribute("ok", "ok");
                return "login";
            } else {
                int CunZai = 1;
                //账号存在，返回error页面
                model.addAttribute("regiest_username", username);
                model.addAttribute("CunZai", CunZai);
                return "error/regisetError";
            }
        }
    }
}
