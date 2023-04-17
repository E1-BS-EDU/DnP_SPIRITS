package com.koko.workweek.kate.workweek.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.koko.workweek.kate.workweek.dto.WeekOfVo;
import com.koko.workweek.kate.workweek.dto.WeeklyWorkVo;

@Mapper
public interface WorkweekMapper {
	WeekOfVo getWeekOf(String selectDate);
	
	WeeklyWorkVo workWeek(String weeklyKey);
	
	void weeklyWorkWrite(WeeklyWorkVo vo);
	
	void weeklyWorkUpdate(WeeklyWorkVo vo);
}
