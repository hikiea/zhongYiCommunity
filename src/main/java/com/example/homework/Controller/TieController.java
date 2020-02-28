package com.example.homework.Controller;


import com.example.homework.Mapper.CommentMapper;
import com.example.homework.Mapper.SecondCommentMapper;
import com.example.homework.Mapper.UserMapper;
import com.example.homework.dto.CommentCreateDTO;
import com.example.homework.dto.CommentDTO;
import com.example.homework.dto.TieDTO;
import com.example.homework.model.SecondComment;
import com.example.homework.model.Tie;
import com.example.homework.model.User;
import com.example.homework.services.CommentService;
import com.example.homework.services.TieService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TieController {

    @Autowired
    private TieService tieService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentCreateDTO commentCreateDTO;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SecondCommentMapper secondCommentMapper;

    @ResponseBody
    @GetMapping("showAllTieByGet")
    @CrossOrigin
    public List<Tie> showAllTieByGet() {
        List<Tie> ties = tieService.adminShow();
        return ties;
    }


    @GetMapping("/tie/{id}")
    public String tie(@PathVariable(name = "id") Integer id,
                      Model model) {

        //展示回复
        List<CommentDTO> comments = commentService.showComment(id);
        model.addAttribute("comments", comments);

        Tie one_Tie = tieService.findById(id);
        model.addAttribute("one_Tie", one_Tie);

        List<SecondComment> secondComments = secondCommentMapper.showAllSecondComment();
        model.addAttribute("secondComments", secondComments);

        //计算回复数
        Integer allComments = commentMapper.count(id);
        model.addAttribute("allComments", allComments);

        return "tie";
    }
}
