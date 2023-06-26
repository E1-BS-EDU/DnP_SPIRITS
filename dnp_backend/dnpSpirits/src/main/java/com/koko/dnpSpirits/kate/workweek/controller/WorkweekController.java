package com.koko.dnpSpirits.kate.workweek.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.dnpSpirits.kate.workweek.dto.WeekOfVo;
import com.koko.dnpSpirits.kate.workweek.dto.WeeklyWorkVo;
import com.koko.dnpSpirits.kate.workweek.service.WorkweekService;

@Controller
@RequestMapping(value = "/workweek")
public class WorkweekController {

	@Autowired
	WorkweekService workweekService;
	
	Logger log = LoggerFactory.getLogger(WorkweekController.class);
	
	/**
     * workweek 페이지
     */
	@GetMapping(path="/workweekPage")
	public String workWeekForm() {
		return "kokoui/workweek/workweekPage";
	}
	// N월 N주차
	@ResponseBody
	@PostMapping(path="/weekOf")
	public HashMap<String, Object> weekOf(String selectDate) throws Exception {
		System.out.println("N월 N주차 조회");
		// 주차 조회
		WeekOfVo weekOfVo = workweekService.getWeekOf(selectDate);
		String year = weekOfVo.getYm().substring(0, 4); // 년
		String month = weekOfVo.getYm().substring(5); // 주차
		String weeklyOf = weekOfVo.getWeekOf();
		String weeklyKey = year+month+"0"+weeklyOf;

		// 주간보고 조회
		WeeklyWorkVo weeklyWorkVo = workweekService.workWeek(weeklyKey);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("weekOf", weekOfVo);
		map.put("weekValue", year+"."+month+" / " + weeklyOf+ "주차");
		map.put("weeklyNo", year+month+"0"+weeklyOf);
		map.put("weeklyWork", weeklyWorkVo);
		
		return map;
	}
	
	@ResponseBody
	@PostMapping(path="/weeklyWorkWrite")
	public void weeklyWorkWrite(WeeklyWorkVo vo) throws Exception {
		System.out.println("주간보고 작성");
		workweekService.weeklyWorkWrite(vo);
	}
	
	@ResponseBody
	@PostMapping(path="/weeklyWorkUpdate")
	public void weeklyWorkUpdate(WeeklyWorkVo vo) throws Exception {
		System.out.println("주간보고 수정");
		System.out.println(vo);
		System.out.println(vo.getSiteNo());
		workweekService.weeklyWorkUpdate(vo);
	}
}
