package com.example.homework.services;

import com.example.homework.Mapper.CommentMapper;
import com.example.homework.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDTO> showComment(Integer id) {
        List<CommentDTO> commentList = commentMapper.findById(id);
        return commentList;
    }

}
