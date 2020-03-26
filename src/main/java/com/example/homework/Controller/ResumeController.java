package com.example.homework.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {

    @GetMapping("/resumeIndex")
    public String toResumeIndex(){
        return "resumeIndex";
    }

    @GetMapping("/resumeContact")
    public String toResumeContact(){
        return "resumeContact";
    }

    @GetMapping("/resumePhoto")
    public String toResumePhoto(){
        return "resumePhoto";
    }

}
