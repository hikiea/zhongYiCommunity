package com.example.homework.Controller;


import com.example.homework.Mapper.TieMapper;
import com.example.homework.Mapper.UserMapper;
import com.example.homework.model.Tie;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EidtController {

    @Autowired
    private TieMapper tieMapper;

    @Autowired
    private UserMapper userMapper;

    //跳转至编辑页面
    @GetMapping("/update/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        Tie tie = tieMapper.findById(id);
        model.addAttribute("title", tie.getTitle());
        model.addAttribute("content", tie.getContent());
        model.addAttribute("id", tie.getId());
        return "edit";
    }

    //更新功能实现
    @PostMapping("/doUpdate")
    public String doPublish(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("content", content);
        model.addAttribute("title", title);
        model.addAttribute("id",id);
        if (content == null || content == "") {
            model.addAttribute("noContent", "内容不能为空！");
            return "edit";
        }
        if (title == null || title == "") {
            model.addAttribute("noTitle", "标题不能为空！");
            return "edit";
        }

        User publish_user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                publish_user = userMapper.findByToken(token);
                break;
            }
        }
        //放入时间
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        //放入内容
        Tie tie = new Tie();
        tie.setTitle(title);
        tie.setContent(content);
        tie.setUsername(publish_user.getUsername());
        tie.setDate(nowTime);
        tie.setId(id);
        tieMapper.updateTie(tie);
        return "redirect:/userCenter/tie";
    }
}
