package com.koko.workweek.kate.workingday.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.workweek.kate.workingday.dto.SiteVo;
import com.koko.workweek.kate.workingday.dto.WorkVo;
import com.koko.workweek.kate.workingday.dto.WorkingdayVo;
import com.koko.workweek.kate.workingday.service.WorkingdayService;
import com.koko.workweek.login.dto.SecurityUser;

@Controller
@RequestMapping(value = "/workingday")
public class WorkingdayController {

	@Autowired
	WorkingdayService wrkdayService;
	
	@GetMapping(value = "/workingdayPage")
	public String workingday(Model model, Authentication authentication) {
        SecurityUser userVo = (SecurityUser) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("gv_user_no",userVo.getUserNo());
		return "kokoui/workingday/workingdayPage"; 
	}
	
	@ResponseBody
	@PostMapping(value = "/saveWorkingDay")
	public int saveWorkingDay(@RequestBody List<WorkingdayVo> workingdayList) {
		for(WorkingdayVo data: workingdayList) {
			System.out.print("saveWorkingDay workDate:: "+data.getWorkDate());
			System.out.print("  siteNo ::  "+data.getSiteNo());
			System.out.print("  gubunCd :: "+data.getGubunCd());
			System.out.println("  userNo :: "+data.getUserNo());
		}
		int res = wrkdayService.saveWorkingDay(workingdayList);
		
		return res;
	}
	
	@ResponseBody
	@PostMapping(value = "/deleteWorkingDay")
	public int deleteWorkingDay(@RequestBody Map<String, Object> workingdayParam) {
		
		System.out.println("saveWorkingDay workDate:: "+workingdayParam.get("workDate"));
		System.out.println("  userNo ::  "+workingdayParam.get("userNo"));
		System.out.println("  id ::  "+workingdayParam.get("id"));
		
		int res = wrkdayService.deleteWorkingDay(workingdayParam);

		return res;
	}
	
	@ResponseBody
	@PostMapping(value = "/selectWorkingDay")
	public List<WorkingdayVo> selectWorkingDay(@RequestParam Map<String, Object> workingdayParam) {
		
		List<WorkingdayVo> workingdayList = wrkdayService.selectWorkingDay(workingdayParam);
		
		return workingdayList;
	}

	@ResponseBody
	@PostMapping(value = "/getGubunCode")
	public List<WorkingdayVo> getGubunCode() {
		
		List<WorkingdayVo> workingdayList = wrkdayService.selectCodeSample();
		
		return workingdayList;
	}
	
	@ResponseBody
	@PostMapping(value = "/workingdayDetail")
	public Map<String, Object> workingdayDetail(@RequestBody WorkingdayVo workingdayVo) {
		
		System.out.println("getUserNo:: "+workingdayVo.getUserNo());
		System.out.println("getWorkDate:: "+workingdayVo.getWorkDate());
		
		Map<String, Object> workingdayInfo = wrkdayService.workingdayDetail(workingdayVo);
		
		return workingdayInfo;
	}
	
	@ResponseBody
	@PostMapping(value = "/selectWorkInfo")
	public List<WorkVo> selectWorkInfo() {
		List<WorkVo> workList = wrkdayService.selectWorkInfo();
		
		return workList;
	}

	@ResponseBody
	@PostMapping(value = "/selectSiteInfo")
	public List<SiteVo> selectSiteInfo() {
		List<SiteVo> siteList = wrkdayService.selectSiteInfo();
		
		return siteList;
	}
	
}
