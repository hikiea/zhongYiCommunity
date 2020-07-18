package com.example.homework.Controller;


import com.example.homework.Mapper.TieMapper;
import com.example.homework.Mapper.UserMapper;
import com.example.homework.model.Tie;
import com.example.homework.model.User;
import com.example.homework.services.TieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class PublishController {

    @Autowired
    private TieMapper tieMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TieService tieService;

    //跳转至发布页面
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    //发布功能实现
    @PostMapping("/doPublish")
    public String doPublish(
            /*获取前端的数据*/
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "username") String username,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        /* 内容校验 */
        if (title == null || title == "") {
            model.addAttribute("noTitle", "标题不能为空！");
            return "publish"; }
        if (content == null || content == "") {
            model.addAttribute("noContent", "内容不能为空！");
            return "publish"; }
        User publish_user = null;
        //放入时间
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //放入内容
        Tie tie = new Tie();
        tie.setTitle(title);
        tie.setContent(content);
        tie.setUsername(username);
        tie.setCreatorId(id);
        tie.setDate(nowTime);
        /* 数据持久化操作 */
        tieMapper.addTie(tie);
        return "redirect:/";
    }
}
