package com.novel.service.data;

import java.util.Date;

import lombok.Data;

@Data
public class UserInfoVO {
    private Integer user_seq ;
    private Integer user_img_seq;
    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_nickname;
    private String user_email;
    private Date user_birth;
    private Integer user_grade;
    private Integer user_status;
}
