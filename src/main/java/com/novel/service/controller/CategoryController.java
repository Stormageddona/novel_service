package com.novel.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @GetMapping("/free")
    public String getFreeNovel()
    {
        return "/category/free" ;
    }
}
