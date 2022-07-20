package com.novel.service.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novel.service.data.UserInfoVO;
import com.novel.service.mapper.NovelMapper;

@Controller
@RequestMapping("/novel")
public class NovelController {
    @Autowired NovelMapper n_mapper;
    @GetMapping("/reg")
    public String getNovelReg(HttpSession session, Model model)
    {
        // if (session.getAttribute("user") == null) return "/" ;
        model.addAttribute("genre_list",n_mapper.selectGenreList() ) ;
        return "/novel/reg" ;
    }

    @GetMapping("/mynovel")
    public String getMyNovel(HttpSession session)
    {
        // if (session.getAttribute("user") == null) return "/" ;
        return "/novel/mynovel" ;
    }

    @GetMapping("/storylist")
    public String getStoryList(HttpSession session,Model model, @RequestParam Integer seq)
    {
        // if (session.getAttribute("user") == null) return "/" ;
        UserInfoVO user = (UserInfoVO)session.getAttribute("user") ;
        model.addAttribute("data",n_mapper.selectNovelInfoBySeq(seq)) ;
        model.addAttribute("favorites",n_mapper.selectNovelFavoritesCount(seq)) ;
        model.addAttribute("favorite",user != null? n_mapper.selectNovelFavoriteUser(seq,user.getUser_seq()):false) ;
        return "/novel/storylist" ;
    }

    @GetMapping("/story/reg")
    public String getStoryReg(HttpSession session,Model model, @RequestParam Integer seq)
    {
        if (session.getAttribute("user") == null) return "/index" ;
        model.addAttribute("seq",seq);
        return "novel/story_reg" ;
    }

    @GetMapping("/story/content/{part}")
    public String getStoryPart(HttpSession session,Model model, @PathVariable Integer part)
    {
        
        return "" ;
    }
}
