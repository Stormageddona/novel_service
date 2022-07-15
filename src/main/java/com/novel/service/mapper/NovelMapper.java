package com.novel.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.novel.service.data.GenreInfoVO;
import com.novel.service.data.WriterInfoVO;
import com.novel.service.data.temp.NovelReg;

@Mapper
public interface NovelMapper {
    // 소설
    public void insertNovelInfo(NovelReg data) ;


    //장르 
    public List<GenreInfoVO> selectGenreList() ;


    //이미지
    public void insertNovelImg(NovelReg data) ;


    // 작가
    public WriterInfoVO selectWriterInfo(Integer seq) ;
    public void insertWriterInfo(WriterInfoVO data) ;
    public void updateNovelInfoWriterSeq(Integer seq) ;
}
