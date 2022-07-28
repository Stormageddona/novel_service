package com.novel.service.data;

import java.util.Date;


import lombok.Data;

@Data
public class CommentInfoVO {
    private Integer cmti_seq;
    private Integer cmti_user_seq;
    private Integer cmti_ns_seq;
    private Integer cmti_cbi_seq;
    private String cmti_text;
    private Integer cmti_status;
    private Date cmti_reg_dt;
    private Date cmti_del_dt;

    private String user_nickname ;
}
