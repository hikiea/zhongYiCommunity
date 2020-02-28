package com.example.homework.Controller;


import com.example.homework.Mapper.SecondCommentMapper;
import com.example.homework.model.SecondComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SecondCommentController {

    @Autowired
    private SecondCommentMapper secondCommentMapper;

    @PostMapping("/doSecondComment")
    public String doSecondComment(
            @RequestParam(value = "commentUUID") String commentUUID,
            @RequestParam(value = "secondCommentContent") String secondCommentContent,
            @RequestParam(value = "parent_id") Integer parent_id,
            @RequestParam(value = "commentUsername") String commentUsername
    ) {
        SecondComment secondComment = new SecondComment();
        secondComment.setContent(secondCommentContent);
        secondComment.setParent_id(parent_id);
        secondComment.setCreate_time(System.currentTimeMillis());
        secondComment.setComment_username(commentUsername);
        secondComment.setParentCommentUUID(commentUUID);
        secondCommentMapper.addSecondCommentMapper(secondComment);
        String url = "redirect:/tie/" + parent_id;
        return url;
    }

    @GetMapping("/showSecondComment")
    public String showSecondComment(
            Model model
    ) {
        String a = "123";
        model.addAttribute("a", a);
        return "tie";
    }

}

