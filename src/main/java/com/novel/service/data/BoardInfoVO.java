package com.novel.service.data;

import java.util.Date;

import lombok.Data;

@Data
public class BoardInfoVO {
    private Integer cbi_seq;
    private Integer cbi_user_seq;
    private Integer cbi_ci_seq;
    private String cbi_name;
    private String cbi_text;
    private String cbi_imgs_seq;
    private Date cbi_reg_dt;
    private Date cbi_mod_dt;
    private Date cbi_del_dt;

    private String nickname ;
}
