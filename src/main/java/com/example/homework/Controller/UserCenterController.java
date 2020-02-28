package com.example.homework.Controller;

import com.example.homework.Mapper.TieMapper;
import com.example.homework.Mapper.UserMapper;
import com.example.homework.dto.TieDTO;
import com.example.homework.model.Tie;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserCenterController {

    @Autowired
    private TieMapper tieMapper;

    @Autowired
    private UserMapper userMapper;

    //点击个人中心，默认进入“我的发帖”
    @GetMapping("/userCenter")
    public String to_UserCenter() {
        return "/userCenter/tie";
    }

    //三个子页面的控制
    @GetMapping("/userCenter/{action}")
    public String profile(
            @PathVariable("action") String action,
            Model model,
            HttpServletRequest request
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        User username = null;
        if ("tie".equals(action)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    username = userMapper.findByToken(token);
                    break;
                }
            }
            List<Tie> yourTie = tieMapper.showOneTie(username.getUsername());
            model.addAttribute("section", "tie");
            model.addAttribute("sectionName", "我的发帖");
            model.addAttribute("yourTie", yourTie);
        } else if ("message".equals(action)) {
            model.addAttribute("section", "message");
            model.addAttribute("sectionName", "正在施工...");

        } else if ("aboutMe".equals(action)) {
            model.addAttribute("section", "aboutMe");
            model.addAttribute("sectionName", "Hi，我是李忠毅");
            model.addAttribute("content", " ~ 中山大学新华学院软件工程大三的学生，目标是成为一名 Java后端工程师" +
                    "  这个论坛,共用时两周，使用了SpringBoot，Thymeleaf，Bootstrap等框架实现，其中，回复帖子是使用前后端分离完成的，但是学得不精，有些许Bug，请谅解\n" +
                    "  感谢你的光临，如果你觉得我做得比以前好的话，可以去微信鼓励一下我呀");
        }
        return "userCenter";
    }
}
