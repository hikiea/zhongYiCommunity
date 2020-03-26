package com.example.homework.Controller;


import com.example.homework.Mapper.HeadMapper;
import com.example.homework.Tool.FileUtil;
import com.example.homework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @Autowired
    private HeadMapper headMapper;

    //跳转到上传文件的页面
    @GetMapping("/updateHead")
    public String goUploadImg(){
        return "updateHead";
    }

    //处理上传的文件
    @PostMapping(value = "/doUpload")
    public String uploadImg(
            // 定义一个 MultipartFile 类型的参数 file
            @RequestParam("file") MultipartFile file,
            @RequestParam("token") String token,
            Model model){
        // 如果图片为空，显示error
        if ((file.getOriginalFilename().isEmpty() )) {
            model.addAttribute("error","error");
            return "updateHead";
        }else{
            User user = new User();
            // 获取图片的名字
            String fileName = file.getOriginalFilename();
            // 防止图片名字一样
            String hToken = UUID.randomUUID().toString();
//             图片存放的文件夹地址（本地服务器使用）
            String filePath = "F:\\IDEA\\Spring_Boot\\zhongYiCommunity\\src\\main\\resources\\static\\picture\\head\\";
//          下面这个在阿里云服务器中使用
//          String filePath = "C:\\Users\\Administrator\\Desktop\\head\\";

            String headName = hToken + fileName;
            try{
                // 图片上传的工具类
                // 参数一：图片的编码格式的字节数组
                // 参数二：图片存放的文件夹地址
                // 参数三：图片的名字
                FileUtil.uploadFile(file.getBytes(),filePath,headName);
                // 把图片名称写入数据库
                user.setToken(token);
                user.setHeadName(headName);
                headMapper.updateUserHead(user);
            } catch (Exception e){
            }
            return "redirect:/";
        }
    }


}
