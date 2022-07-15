package com.novel.service.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novel.service.mapper.NovelMapper;

@Controller
@RequestMapping("/novel")
public class NovelController {
    @Autowired NovelMapper n_mapper;
    @GetMapping("/reg")
    public String novelReg(HttpSession session, Model model)
    {
        // if (session.getAttribute("user") == null) return "/" ;
        model.addAttribute("genre_list",n_mapper.selectGenreList() ) ;
        return "/novel/reg" ;
    }
}
