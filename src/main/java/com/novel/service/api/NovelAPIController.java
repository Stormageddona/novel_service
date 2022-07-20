package com.novel.service.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.service.data.NovelStoryVO;
import com.novel.service.data.UserInfoVO;
import com.novel.service.data.WriterInfoVO;
import com.novel.service.data.temp.NovelReg;
import com.novel.service.data.temp.TextDetail;
import com.novel.service.mapper.NovelMapper;
import com.novel.service.mapper.UserMapper;

@RestController
@RequestMapping("/api/novel")
public class NovelAPIController {
    @Autowired NovelMapper n_mapper ;
    @Autowired UserMapper u_mapper ;

    @PutMapping("/reg")
    @Transactional
    public  Map<String,Object> putNovelInfo(HttpSession session ,@RequestBody NovelReg data)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        UserInfoVO user = (UserInfoVO) session.getAttribute("user") ;
        if (data.getNovel_img() != null) n_mapper.insertNovelImg(data);    
        else data.setNo_img_seq(0);

        n_mapper.insertNovelInfo(data);
        WriterInfoVO writer = new WriterInfoVO(data.getNo_seq(),user.getUser_seq(), user.getUser_nickname()) ;
        n_mapper.insertWriterInfo(writer);
        n_mapper.updateNovelInfoWriterSeq(writer.getWi_seq());
        if (user.getUser_grade() == 1) u_mapper.updateUserInfoGrade(user.getUser_seq(),2);
        
        map.put("status", true) ;
        map.put("message", "소설이 등록되었습니다.") ;
        return map ;

    }

    @GetMapping("/list")
    public  Map<String,Object> getNovelInfo(HttpSession session,@RequestParam @Nullable Integer offset)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        UserInfoVO user = (UserInfoVO) session.getAttribute("user") ;
        if (offset == null) offset = 1 ;
        if (user == null) 
        {
            map.put("status", false) ;
            return map ;
        }
        map.put("message", "목록이 조회되었습니다.") ;
        map.put("list",n_mapper.selectNovelList(user.getUser_seq(),(offset-1)*7)) ;
        map.put("page",n_mapper.selectNovelPagerCount(user.getUser_seq())) ;
        return map ;
    }


    @GetMapping("/storyinfo")
    public  Map<String,Object> getStoryInfo(HttpSession session, @RequestParam Integer seq)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        map.put("data",n_mapper.selectStoryInfoBySeq(seq)) ;

        return map ;
    }


    @GetMapping("/storylist")
    public  Map<String,Object> getStoryList(@RequestParam Integer seq, @RequestParam @Nullable Integer offset)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        List<NovelStoryVO> list = n_mapper.selectNovelStoryList(seq) ;
        for (NovelStoryVO s : list)
        {
            s.setLike_count(n_mapper.selectNovelFavoritesCount(s.getNs_seq()));
        }
        map.put("list",list) ;
        return map ;
    }

    @PutMapping("/story")
    public  Map<String,Object> putStory(@RequestParam Integer seq, @RequestBody TextDetail detail)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        detail.setNs_index(n_mapper.selectStoryCount(seq)+1);

        n_mapper.insertNovelStory(seq,detail) ;

        return map ;
    }
}
