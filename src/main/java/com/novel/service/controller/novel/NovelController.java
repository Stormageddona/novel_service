package com.novel.service.controller.novel;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/novel")
public class NovelController {
    @GetMapping("/reg")
    public String novelReg(HttpSession session)
    {
        if (session.getAttribute("user") == null)
        {
            return "/" ;
        }
        return "/novel/reg" ;
    }
}
