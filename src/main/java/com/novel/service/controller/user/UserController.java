package com.novel.service.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/value/pwd/{seq}")
    public String getChangePwd(@PathVariable String seq,Model model) throws Exception
    {
        model.addAttribute("seq",seq) ;
        return "/user/pwd" ;
    }


}
