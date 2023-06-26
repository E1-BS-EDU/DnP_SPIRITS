package com.koko.dnpSpirits.kate.admin.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.dnpSpirits.kate.admin.dto.AdminVo;
import com.koko.dnpSpirits.kate.admin.dto.CommonCdVo;
import com.koko.dnpSpirits.kate.admin.service.AdminService;
import com.koko.dnpSpirits.kate.notice.dto.NoticeVo;
import com.koko.dnpSpirits.kate.workweek.dto.WeekOfVo;
import com.koko.dnpSpirits.kate.workweek.service.WorkweekService;
import com.koko.dnpSpirits.login.dto.SecurityUser;

@Controller
@RequestMapping(value = "/admin")
public class AdminContcoller {
	@Autowired
	AdminService adminService;
	
	@Autowired
	WorkweekService workweekService;

    @GetMapping(path="/adminPage")
   	public String adminPage() {
   		return "kokoui/admin/adminPage";
   	}
    
    @ResponseBody
    @PostMapping(path="/adminPageListSearch")
   	public HashMap<String, Object> adminPageListSearch(AdminVo adminVo) throws Exception {    	
    	WeekOfVo weekOfVo = workweekService.getWeekOf(adminVo.getSelectDate());
		String year = weekOfVo.getYm().substring(0, 4); // 년
		String month = weekOfVo.getYm().substring(5); // 주차
		String weeklyOf = weekOfVo.getWeekOf();
		String weeklyKey = year+month+"0"+weeklyOf;
		
    	adminVo.setSelectDate(weeklyKey);
    	
    	List<AdminVo> adminList = adminService.getAdminList(adminVo);
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("adminList", adminList);
    	map.put("weekValue", year+"."+month+" / " + weeklyOf+ "주차");
    	
   		return map;
   	}
	
    @ResponseBody
    @PostMapping(path="/adminListModify")
   	public void adminListModify(@RequestBody List<AdminVo> adminList) {
    	System.out.println("수정 시작");
    	adminService.adminListUpdate(adminList);    	
   	}

    
    @GetMapping(path="/commonCdPage")
   	public String commonCdPage() {
   		return "kokoui/admin/commonCdPage";
   	}

    @GetMapping(path="/commonCdDetail")
   	public String commonCdDetail() {
   		return "kokoui/admin/commonCdDetail";
   	} 
    
    
    @ResponseBody
    @PostMapping(path="/commonCdList")
   	public List<CommonCdVo> commonCdList(int page) {
		List<CommonCdVo> commonVo = adminService.commonCdList(page);
   		return commonVo;
   	}
    
    @ResponseBody
    @PostMapping(path="/commonCdMList")
   	public List<CommonCdVo> commonCdMList() {
		List<CommonCdVo> commonVo = adminService.commonCdMList();
   		return commonVo;
   	}

	@ResponseBody
    @PostMapping(path="/commonCdDDetail")
   	public CommonCdVo commonCdDDetail(String comCd) {
		CommonCdVo commonVo = adminService.commonCdDDetail(comCd);
   		return commonVo;
   	}
 
	
    @ResponseBody
    @PostMapping(path="/commonCdMSave")
   	public NoticeVo commonCdMSave(CommonCdVo commonVo ,Authentication authentication) {		
		
		SecurityUser userVo = (SecurityUser) authentication.getPrincipal();  //userDetail 객체를 가져옴


		if(commonVo.getGubun().equals("save")) {
			//commonVo.setVbgCreUserNo(userVo.getUserNo());
			//commonVo.setFinCreUserNo(userVo.getUserNo());
			adminService.commonCdMInsert(commonVo.getCommonList());
		}
		else { 
			//commonVo.setFinCreUserNo(userVo.getUserNo());
			//adminService.commonCdMUpdate(commonVo);
		}
		
   		return null;
   	}
	 
    
    @ResponseBody
    @PostMapping(path="/commonCdDSave")
   	public NoticeVo commonChildCdSave(CommonCdVo commonVo ,Authentication authentication) {		
		
		SecurityUser userVo = (SecurityUser) authentication.getPrincipal();  //userDetail 객체를 가져옴


		if(commonVo.getGubun().equals("save")) {
			commonVo.setVbgCreUserNo(userVo.getUserNo());
			commonVo.setFinCreUserNo(userVo.getUserNo());
			adminService.commonCdDInsert(commonVo);
		}
		else { 
			commonVo.setFinCreUserNo(userVo.getUserNo());
			adminService.commonCdDUpdate(commonVo);
		}
		
   		return null;
   	}
}
