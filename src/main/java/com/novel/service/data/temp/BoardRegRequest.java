package com.novel.service.data.temp;

import java.util.List;

import lombok.Data;

@Data
public class BoardRegRequest {
    private Integer cbi_ci_seq;
    private String cbi_name;
    private String cbi_text;
    private List<String> cbi_imgList;
}
