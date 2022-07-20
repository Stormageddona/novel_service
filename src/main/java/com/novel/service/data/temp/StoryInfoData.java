package com.novel.service.data.temp;

import java.util.Date;

import lombok.Data;

@Data
public class StoryInfoData {
    private Integer storycount ;
    private Integer favoritecount ;
    private Integer totalcount ;
    private Date last_dt ;
}
