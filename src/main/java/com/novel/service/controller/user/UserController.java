package com.novel.service.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novel.service.util.AESAlgorithm;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/join")
    public String getUserJoin()
    {
        return "/user/join" ;
    }
    @GetMapping("/find")
    public String getUserFindIdPwd()
    {
        return "/user/find" ;
    }

    @GetMapping("/find/pwd")
    public String getChangePwd(@RequestParam String seq,Model model) throws Exception
    {
        String temp = AESAlgorithm.Decrypt(seq) ;
        model.addAttribute("seq",temp) ;
        return "/pwd" ;
    }
}
