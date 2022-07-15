package com.novel.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.novel.service.data.UserInfoVO;

@Mapper
public interface UserMapper {
    public UserInfoVO selectUserInfo(String id, String pwd) ;
    public void insertUserInfo(UserInfoVO data) ;
    public String selectFindId(String email, String nickname) ;
    public String selectFindPwd(String email,String id) ;
    public void updateUserInfoPwd(Integer seq,String pwd) ;
    public void updateUserInfoGrade(Integer seq, Integer grade) ;

    public Boolean isDuplicatedId(String id) ;
    public Boolean isDuplicatedEmail(String email) ;
    public Boolean isDuplicatedNickname(String nickname) ;
}
