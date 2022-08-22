package com.novel.service.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.IntArraySerializer;
import com.novel.service.data.BoardInfoVO;
import com.novel.service.data.UserInfoVO;
import com.novel.service.data.ViewInfoVO;
import com.novel.service.data.temp.BoardRegRequest;
import com.novel.service.data.temp.ImageRequestData;
import com.novel.service.mapper.CategoryMapper;
import com.novel.service.mapper.NovelMapper;

@RestController
@RequestMapping("/api/category")
public class CategoryAPIController {
    @Autowired NovelMapper n_mapper ;
    @Autowired CategoryMapper c_mapper ;
    @Value("${spring.servlet.multipart.location}") String path;
    @GetMapping("/free")
    public Map<String,Object> getCategoryNovelList(@RequestParam @Nullable Integer type, @RequestParam @Nullable String keyword)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        if (type == null) type = 0 ;
        map.put("list",n_mapper.selectCategoryNovelList(type,keyword)) ;
        map.put("message", "리스트 조회가 완료되었습니다.") ;
        return map ;
    }

    
    @GetMapping("/community/list")
    public Map<String,Object> getCommunityIndexList()
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        map.put("list",c_mapper.selectBoardList(null,null)) ;
        map.put("message", "리스트 조회가 완료되었습니다.") ;
        return map ;
    }

    @GetMapping("/post")
    public Map<String,Object> getPost(@RequestParam Integer seq) throws Exception
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;

        BoardInfoVO data = c_mapper.selectPostBySeq(seq) ;
        map.put("data", data) ;

        // 이미지 로딩
        if (data.getCbi_imgs_seq() != null && !data.getCbi_imgs_seq().equals(""))
        {
            List<String> imgs = new LinkedList<String>() ;
            String split[] = data.getCbi_imgs_seq().split(",") ;
            for (String i : split)
            {
                // System.out.println(i);
                imgs.add(c_mapper.selectImageDataBySeq(Integer.parseInt(i))) ;
            }
            map.put("imgs",imgs) ;
        }
        // 텍스트 로딩
        BufferedReader br = new BufferedReader(
            new FileReader(new File(path+"/text/"+data.getCbi_text()) 
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
        return map ;
    }

    @PutMapping("/post")
    @Transactional
    public Map<String,Object> putPost(@RequestBody BoardRegRequest data,HttpSession session) throws Exception
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        UserInfoVO user = (UserInfoVO)session.getAttribute("user") ;
        BoardInfoVO bidata = new BoardInfoVO() ;
        bidata.setCbi_imgs_seq("");
        if (data.getCbi_imgList() != null && data.getCbi_imgList().size() > 0)
        {
            List<String> imglist = data.getCbi_imgList() ;
            for (String i : imglist)
            {
                ImageRequestData s = new ImageRequestData() ;
                s.setImg(i);
                c_mapper.insertPostImg(s);
                bidata.setCbi_imgs_seq(bidata.getCbi_imgs_seq() + (bidata.getCbi_imgs_seq()==""?"":",") + s.getSeq().toString());

                File from = new File(path+"/images/temp/"+i) ;
                File target = new File(path+"/images/"+i) ;
                Files.move(from.toPath(),target.toPath(),StandardCopyOption.REPLACE_EXISTING) ;
            }
        }

        bidata.setCbi_user_seq(user.getUser_seq());
        bidata.setCbi_ci_seq(data.getCbi_ci_seq());
        bidata.setCbi_name(data.getCbi_name());
        bidata.setCbi_text(data.getCbi_text());
        c_mapper.insertPostData(bidata) ;
        map.put("status",true) ;
        map.put("message","게시글 등록이 완료되었습니다.") ;
        return map ;
    }

    @GetMapping("/board")
    public Map<String,Object> getBoard(@RequestParam Integer seq ,@RequestParam @Nullable String keyword)
    {
        Map<String,Object> map = new LinkedHashMap<String,Object>() ;
        map.put("list",c_mapper.selectBoardList(seq,keyword)) ;
        map.put("message", "리스트 조회가 완료되었습니다.") ;
        return map ;
    }



    // @GetMapping("/search")
    // public Map<String,Object> getSearchText(@RequestParam String keyword)
    // {
    //     Map<String,Object> map = new LinkedHashMap<String,Object>() ;

    //     List<ViewInfoVO> list = n_mapper.selectKeywordSearchView(keyword) ;

    //     map.put("list",list) ;
    //     map.put("message", "검색이 완료되었습니다.") ;
    //     return map ;
    // }

}
