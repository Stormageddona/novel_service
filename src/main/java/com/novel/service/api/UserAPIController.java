package com.novel.service.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.service.data.CommentInfoVO;
import com.novel.service.data.UserInfoVO;
import com.novel.service.mapper.UserMapper;
import com.novel.service.util.AESAlgorithm;

@RestController
@RequestMapping("/api/user")
public class UserAPIController {
    @Autowired UserMapper u_mapper ;
    // 로그인, 로그아웃
    @GetMapping("/login")
    public Map<String,Object> getUserLogin(@RequestParam @Nullable String id, @RequestParam @Nullable String pwd,HttpSession session) throws Exception
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        if (id == null || pwd == null) 
        {
            map.put("status",false) ;
            map.put("message","아이디와 비밀번호를 입력해주세요.") ;
            return map ;
        }
        pwd = AESAlgorithm.Encrypt(pwd) ;
        UserInfoVO user = u_mapper.selectUserInfo(id, pwd) ;
        if (user == null)
        {
            map.put("status",false) ;
            map.put("message","아이디나 비밀번호가 맞지않습니다.") ;
            return map ;
        }
        map.put("status",true) ;
        map.put("message","로그인이 성공했습니다.") ;
        session.setAttribute("user", user);
        return map ;
    }

    @GetMapping("/logout")
    public void getUserLogout(HttpSession session)
    {
        session.invalidate();

    }

    //회원가입
    @PutMapping("/join")
    public Map<String,Object> putUser(@RequestBody UserInfoVO data) throws Exception
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        data.setUser_pwd(AESAlgorithm.Encrypt(data.getUser_pwd()));
        if (data.getUser_nickname() == null) data.setUser_nickname(data.getUser_name());
        u_mapper.insertUserInfo(data);
        map.put("status",true) ;
        map.put("message","회원가입이 완료되었습니다.") ;
        return map ;
    }
    // 회원가입 중복 조회
    @GetMapping("/join")
    public Map<String,Object> DuplicatedChk(@RequestParam String type, @RequestParam String value)
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        

        if (u_mapper.isDuplicatedId(value) && type.equals("id"))
        {
            map.put("status", false) ;
            map.put("message", "사용중인 아이디 입니다.") ;
            return map ;
        }
        else if (u_mapper.isDuplicatedEmail(value) && type.equals("email"))
        {
            map.put("status", false) ;
            map.put("message", "사용중인 이메일 입니다.") ;
            return map ;
        }
        else if (u_mapper.isDuplicatedNickname(value) && type.equals("nickname"))
        {
            map.put("status", false) ;
            map.put("message", "사용중인 닉네임 입니다.") ;
            return map ;
        }
        map.put("status", true) ;
        map.put("message", "사용 가능 합니다.") ;
        return map ;

    }
    
    // 아이디 비밀번호 찾기
    @GetMapping("/find")
    public Map<String,Object> findUser(@RequestParam String value1, @RequestParam String value2,@RequestParam Integer type) throws Exception
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        String temp = null ;
        if (type == 1) 
        {
            temp = u_mapper.selectFindId(value1, value2) ;
            if (temp == null) 
            {
                map.put("status",false) ;
                map.put("message","아이디 찾기에 실패하였습니다.") ;
                return map ;
            }
            map.put("status",true) ;
            map.put("id",u_mapper.selectFindId(value1, value2)) ;
        }
        else if (type == 2) 
        {
            map.put("status",true) ;
            temp = u_mapper.selectFindPwd(value2, value1) ;
            if (temp == null) 
            {
                map.put("status",false) ;
                map.put("message","비밀번호 찾기에 실패하였습니다.") ;
                return map ;
            }
            map.put("status",true) ;
            map.put("seq",AESAlgorithm.Encrypt(temp)) ;
        }

        return map ;
    }

    @PatchMapping("/pwd/{seq}")
    public Map<String,Object> updateUserPwd(@PathVariable String seq,@RequestParam String pwd) throws Exception
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        seq = AESAlgorithm.Decrypt(seq) ;
        pwd = AESAlgorithm.Encrypt(pwd) ;
        Integer tempseq = Integer.parseInt(seq) ;
        u_mapper.updateUserInfoPwd(tempseq, pwd) ;
        map.put("status", true) ;
        map.put("message", "비밀번호 변경이 완료되었습니다.") ;
        return map ;
    }



    //댓글 작성
    @PutMapping("/comment")
    public Map<String,Object> putComment(HttpSession session,@RequestParam Integer type , @RequestParam Integer seq , @RequestBody String value)
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        UserInfoVO user = (UserInfoVO)session.getAttribute("user") ;
        String temp = "";
        if (user == null) 
        {
            map.put("status", false) ;
            map.put("message", "로그인을 다시 해주세요.") ;
            return map ;
        }
        if (user.getUser_status() ==  2)  temp = "이용이 정지된 사용자 입니다.";
        else if (user.getUser_status() ==  3)  temp = "영구정지된 사용자 입니다";
        else if (user.getUser_status() ==  4)  temp = "탈퇴대기중인 사용자 입니다.";
        if (user.getUser_status() !=  1)
        {
            map.put("status", false) ;
            map.put("message", "댓글 입력에 실패하였습니다<br>사유 : " + temp) ;
            return map ;
        }
        try {
            u_mapper.insertUserComment(type,user.getUser_seq(),seq,value) ;
        } catch (Exception e) {
            map.put("status", false) ;
            map.put("message", "댓글 입력에 실패하였습니다") ;
            System.out.println(e);
            return map ;
        }
        
        map.put("status", true) ;
        map.put("message", "댓글 입력에 성공하였습니다.") ;
        return map ;
    }

    // 댓글 조회
    @GetMapping("/comment")
    public Map<String,Object> getComment(HttpSession session,@RequestParam Integer type , @RequestParam Integer seq)
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        
        List<CommentInfoVO> list = u_mapper.selectCommentList(type, seq) ;
        for(CommentInfoVO templist : list)
        {
            if (templist.getCmti_status() == 2)
                templist.setCmti_text("관리자에 의해 블라인드 된 댓글입니다."); 
            else if (templist.getCmti_status() == 3)
                templist.setCmti_text("삭제 된 댓글입니다."); 
        }

        map.put("status", true) ;
        map.put("message", "댓글 조회 완료") ;
        map.put("list",list) ;
        
        return map ;
    }
}
