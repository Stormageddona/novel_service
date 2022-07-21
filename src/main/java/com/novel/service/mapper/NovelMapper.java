package com.novel.service.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.novel.service.data.GenreInfoVO;
import com.novel.service.data.NovelInfoVO;
import com.novel.service.data.NovelStoryVO;
import com.novel.service.data.WriterInfoVO;
import com.novel.service.data.temp.MyNovelList;
import com.novel.service.data.temp.NovelReg;
import com.novel.service.data.temp.StoryInfoData;
import com.novel.service.data.temp.TextDetail;

@Mapper
public interface NovelMapper {
    // 소설
    public void insertNovelInfo(NovelReg data) ;
    public List<MyNovelList> selectNovelList(Integer seq,Integer offset) ;
    public Integer selectNovelPagerCount(Integer seq) ;

    public NovelInfoVO selectNovelInfoBySeq(Integer seq) ;

    public Boolean selectNovelFavoriteUser(Integer seq, Integer user) ;
    public Integer selectNovelFavoritesCount(Integer seq) ;

    public List<NovelStoryVO> selectNovelStoryList(Integer seq) ;
    public void insertNovelStory(Integer seq, TextDetail data) ;
    public Integer selectNovelLikeCount(Integer seq) ;
    public NovelStoryVO selectStoryPart(Integer seq) ;
    

    public Integer selectStoryCount(Integer seq);
    public StoryInfoData selectStoryCountBySeq(Integer seq) ;

    public List<NovelInfoVO> selectCategoryNovelList(Integer type) ;

    public void insertNovelCountData(Integer seq,Integer no_seq , Integer ns_seq) ;
    
    //조회수
    public Boolean isNovelCountTime(Integer user_seq) ;
    public Integer selectNovelCountData(Integer ns_seq) ;

    //장르 
    public List<GenreInfoVO> selectGenreList() ;


    //이미지
    public void insertNovelImg(NovelReg data) ;


    // 작가
    public WriterInfoVO selectWriterInfo(Integer seq) ;
    public void insertWriterInfo(WriterInfoVO data) ;
    public void updateNovelInfoWriterSeq(Integer seq, Integer no_seq) ;
}
