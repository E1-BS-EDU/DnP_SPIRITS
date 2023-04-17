package com.koko.workweek.kate.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koko.workweek.kate.notice.dto.NoticeVo;
import com.koko.workweek.kate.notice.mapper.NoticeMapper;
import com.koko.workweek.login.dto.SecurityUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {
	
	@Autowired
    NoticeMapper noticeMapper;
	
	/*공지사항 리스트 조회 */
	public List<NoticeVo> getNoticeList(int page) {
		
		List<NoticeVo> noticeVo = noticeMapper.getNoticeListMapper(page);
		
		if(noticeVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return noticeVo;
	}
	
	/*공지사항 상세 조회 */
	public NoticeVo getNoticeDetail(int no) {
		
		NoticeVo noticeVo = noticeMapper.getNoticeDetailMapper(no);
		
		if(noticeVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return noticeVo;
	}
	
	/*공지사항 저장 */
	public void noticeDetailSave(NoticeVo noticeVo) {	
		noticeMapper.noticeDetailSaveMapper(noticeVo);
	}
	/*공지사항 수정 */
	public void noticeDetailUpdate(NoticeVo noticeVo) {	
		noticeMapper.noticeDetailUpdateMapper(noticeVo);
	}

	
}
