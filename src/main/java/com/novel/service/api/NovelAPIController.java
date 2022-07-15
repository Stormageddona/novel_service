package com.novel.service.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novel.service.data.UserInfoVO;
import com.novel.service.data.WriterInfoVO;
import com.novel.service.data.temp.NovelReg;
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
        WriterInfoVO writer = new WriterInfoVO(data.getNo_seq(),user.getUser_seq()) ;
        n_mapper.insertWriterInfo(writer);
        n_mapper.updateNovelInfoWriterSeq(writer.getWi_seq());
        if (user.getUser_grade() == 1) u_mapper.updateUserInfoGrade(user.getUser_seq(),2);
        
        map.put("status", true) ;
        map.put("message", "소설이 등록되었습니다.") ;
        return map ;

    }
}
