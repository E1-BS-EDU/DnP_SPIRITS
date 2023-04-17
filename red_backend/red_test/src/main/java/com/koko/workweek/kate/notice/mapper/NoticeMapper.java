package com.koko.workweek.kate.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koko.workweek.kate.notice.dto.NoticeVo;

@Mapper
public interface NoticeMapper {
	/*공지사항 목록 조회*/
	List<NoticeVo> getNoticeListMapper(int page);	
	
	/*공지사항 상세 조회*/
	NoticeVo getNoticeDetailMapper(int no);	
	
	/*공지사항 저장*/
	void noticeDetailSaveMapper(NoticeVo noticeVo);
	
	/*공지사항 수정*/
	void noticeDetailUpdateMapper(NoticeVo noticeVo);
}
