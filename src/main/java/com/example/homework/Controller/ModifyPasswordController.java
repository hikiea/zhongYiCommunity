package com.example.homework.Controller;

import com.example.homework.Mapper.UserMapper;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ModifyPasswordController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/modifyPassword")
    public String goModify() {
        return "modifyPassword";
    }

    //    @ResponseBody
    @PostMapping("/modify")
    public String doModify(@RequestParam(value = "oldPassword") String oldPassword,
                           @RequestParam(value = "newPassword") String newPassword,
                           @RequestParam(value = "userId") Integer userId) {
        User user = userMapper.findByUserId(userId);
        if (oldPassword.equals(user.getPassword())) {
            userMapper.updateById(newPassword, userId);
            return "login";
        } else {
            return "error/modifyPasswordError";
        }
    }

}
