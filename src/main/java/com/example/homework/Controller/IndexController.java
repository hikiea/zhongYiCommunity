package com.example.homework.Controller;


import com.example.homework.Mapper.TieMapper;
import com.example.homework.Mapper.UserMapper;
import com.example.homework.dto.PageDTO;
import com.example.homework.dto.Result;
import com.example.homework.dto.TieDTO;
import com.example.homework.model.Tie;
import com.example.homework.model.User;
import com.example.homework.services.UserAllJson;
import com.example.homework.services.UserList;
import com.example.homework.services.UserOneJson;
import com.example.homework.services.TieService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TieMapper tieMapper;

    @Autowired
    private UserList userlist;

    @Autowired
    private UserAllJson userAllJson;

    @Autowired
    private UserOneJson userOneJson;

    @Autowired
    private TieService tieService;

    //退出登录
    @GetMapping("/logout")
    public String go_index(
            HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }

    //  刷新页面
    @GetMapping("/flush")
    public String toPublish() {
        return "redirect:/";
    }


    //    用Json格式显示所有用户数据
    @ResponseBody
    @GetMapping("/showAllUserByGet")
    @CrossOrigin
    public Result<List<User>> toShowAllJson() {
        //创建一个result对象，调用service层中的方法，获取其返回值，放进result对象，然后返回到页面
        Result<List<User>> result = userAllJson.getAllJson();
        return result;
    }

    //    用Json格式显示一个用户数据
    @ResponseBody
    @GetMapping("/showOneJson")
    public Result<List<User>> toShowJson(@Param("id") Integer id) {
        Result<List<User>> result = userOneJson.showOneJson(id);
        return result;
    }

//    每次进入主页，刷新主页显示内容
    @GetMapping("/")
    public String toIndex(Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PageDTO pageNation = tieService.list(page, size);
        model.addAttribute("pageNation", pageNation);
        return "index";
    }

    //跳转至指定页面
    @PostMapping("/toPage")
    public String toPage(@RequestParam(value = "page") String page) {
        String go = "redirect:/" + "?page=" + page;
        return go;
    }

    //页面搜索功能
    @PostMapping("/selectTitle")
    public String select(@RequestParam(value = "selectByTitle") String title,
                         Model model) {
        List<TieDTO> selectResult = tieService.selectByTitle(title);
        model.addAttribute("selectResult", selectResult);
        return "index";
    }


}
