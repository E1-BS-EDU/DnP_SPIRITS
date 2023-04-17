package com.koko.workweek.kate.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.workweek.kate.notice.dto.NoticeVo;
import com.koko.workweek.kate.notice.service.NoticeService;
import com.koko.workweek.login.dto.SecurityUser;

@Controller
@RequestMapping(value = "/notice")
public class NoticeContcoller {
	
	@Autowired
	NoticeService noticeService;
	
    @GetMapping(path="/noticePage")
   	public String noticepage() {
   		return "kokoui/notice/noticePage";
   	}

    @GetMapping(path="/noticeDetail")
  	public String noticeDetailpage() {
  		return "kokoui/notice/noticeDetail";
  	}
    
	@ResponseBody
    @PostMapping(path="/noticeList")
   	public List<NoticeVo> noticeList(int page) {
		List<NoticeVo> noticeVo = noticeService.getNoticeList(page);
   		return noticeVo;
   	}
   	

	@ResponseBody
    @PostMapping(path="/noticeDetail")
   	public NoticeVo noticeDetail(int no) {
		NoticeVo noticeVo = noticeService.getNoticeDetail(no);
   		return noticeVo;
   	}
   	
	
	@ResponseBody
    @PostMapping(path="/noticeDetailSave")
   	public NoticeVo noticeDetailSave(NoticeVo noticeVo ,Authentication authentication) {		
		
		 SecurityUser userVo = (SecurityUser) authentication.getPrincipal();  //userDetail 객체를 가져옴
		
		 System.out.println("userVo.getUserNo()::::::::"+userVo.getUsername());
		 
		if(noticeVo.getGubun().equals("save")) {
			 noticeVo.setVbgCreUserNo(userVo.getUserNo());
			 noticeVo.setFinCreUserNo(userVo.getUserNo());
			noticeService.noticeDetailSave(noticeVo);
		}
		else { 
			noticeVo.setFinCreUserNo(userVo.getUserNo());
			noticeService.noticeDetailUpdate(noticeVo);
		}
		
   		return null;
   	}
	

   	
        
}
