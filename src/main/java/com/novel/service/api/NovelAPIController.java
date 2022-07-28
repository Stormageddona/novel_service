package com.novel.service.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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
    @Value("${spring.servlet.multipart.location}") String path;

    @PutMapping("/reg")
    @Transactional
    public  Map<String,Object> putNovelInfo(HttpSession session ,@RequestBody NovelReg data)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        UserInfoVO user = (UserInfoVO) session.getAttribute("user") ;
        if (data.getNovel_img() != null) n_mapper.insertNovelImg(data);    
        else data.setNo_img_seq(0);
        String temp = user.getUser_nickname().equals("")||user.getUser_nickname()==null?user.getUser_name():user.getUser_nickname() ;
        n_mapper.insertNovelInfo(data);
        WriterInfoVO writer = new WriterInfoVO(data.getNo_seq(),user.getUser_seq(), temp) ;
        n_mapper.insertWriterInfo(writer);
        n_mapper.updateNovelInfoWriterSeq(writer.getWi_seq(), data.getNo_seq());
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
            map.put("message", "로그인 해주세요.") ;
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
        map.put("data",n_mapper.selectStoryCountBySeq(seq)) ;

        return map ;
    }


    @GetMapping("/storylist")
    public  Map<String,Object> getStoryList(@RequestParam Integer seq, @RequestParam @Nullable Integer offset)  
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        List<NovelStoryVO> list = null;
        try {
            list = n_mapper.selectNovelStoryList(seq) ;
            
        } catch (Exception e) {
            map.put("list",null) ;
        }
        
        for (NovelStoryVO s : list)
        {
            s.setLike_count(n_mapper.selectNovelFavoritesCount(s.getNs_seq()));
            s.setNs_count(n_mapper.selectNovelCountData(s.getNs_seq()));
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

        map.put("status",true) ;
        map.put("message","소설이 등록 되었습니다.") ;


        return map ;
    }
    @GetMapping("/story/part")
    public  Map<String,Object> getStoryPart(@RequestParam Integer seq, HttpSession session) throws Exception
    {
        UserInfoVO user = (UserInfoVO)session.getAttribute("user") ;
        NovelStoryVO data = n_mapper.selectStoryPart(seq) ;
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        map.put("title", data.getNs_name()) ;

        BufferedReader br = new BufferedReader(
            new FileReader(new File(path+"/text/"+data.getNs_file_name()) 
        ))  ;
        String s = "";
        String content = "";
        while(s != null) 
        {
            content += s + "<br>";
            s = br.readLine();
        }
        br.close();
        map.put("content", content) ;

        
        if (user != null  )
        {
            Boolean temp = n_mapper.isNovelCountTime(user.getUser_seq(), data.getNs_seq()) ;
            if (temp == null) temp = true ;
            if (temp)
                n_mapper.insertNovelCountData(user.getUser_seq(), data.getNs_no_seq(),seq) ;
        }
        br = null ;
        try {
            br = new BufferedReader(
                new FileReader(new File(path+"/text/"+data.getNs_writer_comment()) 
            ))  ;
            s = "";
            content = "";
            while(s != null) 
            {
                content += s + "<br>";
                s = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            map.put("comment", "-") ;
            return map ;
        }

        map.put("comment", content) ;
        return map ;
    }

    

    

}
