package com.example.homework.Controller;

import com.example.homework.Mapper.TieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {

    @Autowired
    private TieMapper tieMapper;

    @PostMapping("/deleteTie")
    public String toDeleteTie(@RequestParam(name = "deleteId") Integer deleteId) {
        tieMapper.deleteTie(deleteId);
        return "redirect:/userCenter/tie";
    }

    @PostMapping("/AdminDeleteTie")
    public String AdminToDeleteTie(@RequestParam(name = "AdminDeleteId") Integer AdminDeleteId) {
        tieMapper.deleteTie(AdminDeleteId);
        return "redirect:/";
    }

}
