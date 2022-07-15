package com.novel.service.data;

import java.util.Date;

import lombok.Data;

@Data
public class NovelInfoVO {
    private Integer no_seq;
    private Integer no_img_seq;
    private Integer no_gen_seq;
    private Integer no_wi_seq ;
    private String no_name;
    private Integer no_status;
    private String no_simple_txt;
    private Integer no_serial_type;
    private String no_seday;
    private String no_publisher;
    private Integer no_age_status;
    private Date no_reg_dt;

    private String novel_img ;

}
