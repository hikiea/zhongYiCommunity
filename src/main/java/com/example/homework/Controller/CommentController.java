package com.example.homework.Controller;


import com.example.homework.Mapper.CommentMapper;
import com.example.homework.dto.CommentCreateDTO;
import com.example.homework.dto.ResultDTO;
import com.example.homework.model.Comment;
import com.example.homework.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @PostMapping("/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO) {
        String commentUUID = UUID.randomUUID().toString();
        Comment comment = new Comment();
        Integer tieId = commentCreateDTO.getParentId();
        comment.setParent_id(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setComment_id(commentCreateDTO.getComment_id());
        comment.setComment_username(commentCreateDTO.getComment_username());
        comment.setModify_time(System.currentTimeMillis());
        comment.setCreate_time(System.currentTimeMillis());
        comment.setLike_count(0);
        comment.setCommentUUID(commentUUID);
        commentMapper.insert(comment);
        String Url = "redirect:/tie/" + tieId;
        return Url;
    }


    @ResponseBody
    @RequestMapping(value = "/comment{id}", method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id) {
        return null;
    }

}
