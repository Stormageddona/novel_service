package com.novel.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novel.service.mapper.CategoryMapper;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired CategoryMapper c_mapper ;
    @GetMapping("/free")
    public String getFreeNovel()
    {
        return "/category/free" ;
    }

    @GetMapping("/community")
    public String getCommunity()
    {
        return "/category/community" ;
    }

    @GetMapping("/board_free")
    public String getBoardFree()
    {
        return "/category/board_free" ;
    }  

    @GetMapping("/bookreview")
    public String getBoardBookReview()
    {
        return "/category/bookreview" ;
    }   

    @GetMapping("/talk")
    public String getBoardTalk()
    {
        return "/category/talk" ;
    }  
    
    @GetMapping("/bookreport")
    public String getBoardBookReport()
    {
        return "/category/bookreport" ;
    }

    @GetMapping("/board_reg")
    public String getBoardReg(Model model, @RequestParam Integer seq, @RequestParam String adr)
    {

        model.addAttribute("seq",seq) ;
        model.addAttribute("adr",adr) ;
        model.addAttribute("category",c_mapper.selectCategoryList()) ;
        return "/category/board_reg" ;
    }
    @GetMapping("/post")
    public String getPost(Model model, @RequestParam Integer seq)
    {
        model.addAttribute("seq",seq) ;
        return "/category/post" ;
    }
}
