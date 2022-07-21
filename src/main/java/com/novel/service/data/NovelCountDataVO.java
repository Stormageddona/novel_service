package com.novel.service.data;

import java.util.Date;

import lombok.Data;

@Data
public class NovelCountDataVO {
    private Integer ncd_seq;
    private Integer ncd_user_seq;
    private Integer ncd_ns_seq;
    private Date ncd_count_time;
}
