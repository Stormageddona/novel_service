package com.novel.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.novel.service.data.BoardInfoVO;
import com.novel.service.data.CategoryInfoVO;
import com.novel.service.data.temp.ImageRequestData;

@Mapper
public interface CategoryMapper {
    public List<CategoryInfoVO> selectCategoryList() ;
    public List<BoardInfoVO> selectBoardList(Integer type,String keyword) ;
    public BoardInfoVO selectPostBySeq(Integer type) ;
    public void insertPostImg(ImageRequestData data) ;
    public void insertPostData(BoardInfoVO data) ;
    public String selectImageDataBySeq(Integer seq) ;
}
