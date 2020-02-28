package com.example.homework.Controller;


import com.example.homework.Mapper.UserMapper;
import com.example.homework.model.User;
import com.example.homework.services.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    //实现自动装配
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserList userlist;

    //验证账号密码
    @PostMapping("/login")
    //获取三个参数，账号，密码，按钮是否被点击
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletResponse response,
                        Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //使用findByUsername_login（）方法，对数据库进行检查，判断账号密码是否正确
        User check_login = userMapper.findByUsername_login(username, password);
        //正确，返回index页面，错误，返回error页面,写cookie和session
        if (check_login != null) {
            model.addAttribute("success_login_username", username);
            User user_token = userMapper.username_to_setToken(username);
            String token = user_token.getToken();
            if (user != null) {
                //把user对象放进cookie
                Cookie cookie = new Cookie("token", token);
                //这里设置cookie的存活时间
                cookie.setMaxAge(360 * 24 * 60 * 60);
                response.addCookie(cookie);
                return "redirect:/";
            } else {
                return "redirect:/";
            }
        } else {
            return "error/loginError";
        }
    }

    //返回登录页面
    @GetMapping("/login")
    public String return_login() {
        return "login";
    }

    //返回注册页面
    @GetMapping("/regiest")
    public String return_regiest() {
        return "regiest";
    }
}
