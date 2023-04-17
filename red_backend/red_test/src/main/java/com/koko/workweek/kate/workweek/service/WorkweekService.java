package com.koko.workweek.kate.workweek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koko.workweek.kate.workweek.dto.WeekOfVo;
import com.koko.workweek.kate.workweek.dto.WeeklyWorkVo;
import com.koko.workweek.kate.workweek.mapper.WorkweekMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkweekService {
	
	@Autowired
	WorkweekMapper workweekMapper;
	
	public WeekOfVo getWeekOf(String selectDate) throws Exception {
		WeekOfVo weekOfVo = workweekMapper.getWeekOf(selectDate);
		return weekOfVo;
	}
	
	public WeeklyWorkVo workWeek(String weeklyKey) throws Exception {
		WeeklyWorkVo weeklyWorkVo = workweekMapper.workWeek(weeklyKey);
		return weeklyWorkVo;
	}
	
	public void weeklyWorkWrite(WeeklyWorkVo vo) {
		workweekMapper.weeklyWorkWrite(vo);
	}
	
	public void weeklyWorkUpdate(WeeklyWorkVo vo) {
		workweekMapper.weeklyWorkUpdate(vo);
	}
	
}
