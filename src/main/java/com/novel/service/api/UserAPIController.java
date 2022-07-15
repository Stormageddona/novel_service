package com.novel.service.api;

import java.util.LinkedHashMap;
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
        u_mapper.insertUserInfo(data);
        map.put("status",true) ;
        map.put("message","회원가입이 완료되었습니다.") ;
        return map ;
    }
        
    // 아이디 비밀번호 찾기
    @GetMapping("/find")
    public Map<String,Object> findUser(@RequestParam String value1, @RequestParam String value2,@RequestParam Integer type) throws Exception
    {
        Map<String,Object> map =new LinkedHashMap<String,Object>() ;
        if (type == 1) 
        {
            map.put("status",true) ;
            map.put("id",u_mapper.selectFindId(value1, value2)) ;
        }
        else if (type == 2) 
        {
            map.put("status",true) ;
            map.put("seq",AESAlgorithm.Encrypt(u_mapper.selectFindPwd(value2, value1))) ;
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

    // 회원가입
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
}
