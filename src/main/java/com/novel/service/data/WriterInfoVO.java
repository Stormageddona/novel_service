package com.novel.service.data;

import lombok.Data;

@Data
public class WriterInfoVO {
    private Integer wi_seq;
    private Integer wi_user_seq;
    private Integer wi_no_seq;

    public WriterInfoVO(Integer no_seq, Integer wi_user_seq){
        this.wi_no_seq = no_seq;
        this.wi_user_seq = wi_user_seq;
    }
}
