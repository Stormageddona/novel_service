package com.novel.service.data;

import java.util.Date;

import lombok.Data;

@Data
public class NovelStoryVO {
    private Integer ns_seq;
    private Integer ns_no_seq;
    private String ns_name;
    private String ns_file_name;
    private Integer ns_index;
    private String ns_writer_comment;
    private Date ns_reg_dt;
    private Date ns_mod_dt;
    
    
    private Integer ns_count ;
    private Integer like_count ;
}
