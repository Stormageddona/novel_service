package com.novel.service.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.novel.service.data.NovelInfoVO;
import com.novel.service.mapper.NovelMapper;

@RestController
@RequestMapping("/api/category")
public class CategoryAPIController {
    @Autowired NovelMapper n_mapper ;
    @GetMapping("/free")
    public Map<String,Object> getCategoryNovelList(@RequestParam Integer type)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;


        map.put("list",n_mapper.selectCategoryNovelList(type)) ;
        map.put("message", "리스트 조회가 완료되었습니다.") ;
        return map ;
    }
    
}
