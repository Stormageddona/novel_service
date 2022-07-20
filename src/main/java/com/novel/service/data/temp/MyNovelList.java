package com.novel.service.data.temp;

import java.util.Date;

import lombok.Data;

@Data
public class MyNovelList {
    private Integer no_seq ;
    private String no_name;
    private Integer no_status;
    private Integer no_serial_type;
    private Integer no_age_status;
    private Date no_reg_dt;
    private String img_name;
    private String gen_name;
    
    private Integer wi_user_seq;

}
