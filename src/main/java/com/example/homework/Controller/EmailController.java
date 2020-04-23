package com.example.homework.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    JavaMailSenderImpl mailSender;

    @PostMapping("/sendEmail")
    public String doSend(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "emailTitle") String emailTitle,
            @RequestParam(value = "emailContent") String emailContent,
            Model model
    ){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(emailTitle);
            mailMessage.setText(emailContent + "————————发送人邮箱：" + email);
            mailMessage.setTo("594183034@qq.com");
            mailMessage.setFrom("594183034@qq.com");
            mailSender.send(mailMessage);
            model.addAttribute("msg","发送成功！");
            return "resumeContact";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg","发送失败！");
            return "resumeContact";
        }
    }

}
