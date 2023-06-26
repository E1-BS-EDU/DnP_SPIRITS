package com.koko.dnpSpirits.kate.workingday.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koko.dnpSpirits.kate.workingday.dto.SiteVo;
import com.koko.dnpSpirits.kate.workingday.dto.WorkVo;
import com.koko.dnpSpirits.kate.workingday.dto.WorkingdayVo;
import com.koko.dnpSpirits.kate.workingday.mapper.WorkingdayMapper;

@Service
public class WorkingdayService {

	@Autowired
	WorkingdayMapper wrkDayMapper;
	
	public int saveWorkingDay(List<WorkingdayVo> workingdayList) {
		int res = wrkDayMapper.saveWorkingDay(workingdayList);
		return res;
	}
	
	public int deleteWorkingDay(Map<String, Object> workingdayParam) {
		int res = wrkDayMapper.deleteWorkingDay(workingdayParam);
		return res;
	}
	
	public List<WorkingdayVo> selectWorkingDay(Map<String, Object> workingdayParam){
		List<WorkingdayVo> workingdayList = wrkDayMapper.selectWorkingDay(workingdayParam);
		
		return workingdayList;
	}
	
	public List<WorkingdayVo> selectCodeSample(){
		List<WorkingdayVo> workingdayList = wrkDayMapper.selectCodeSample();
		
		return workingdayList;
	}
	
	public Map<String, Object> workingdayDetail(WorkingdayVo workingdayVo) {
		Map<String, Object> workingdayInfo = wrkDayMapper.workingdayDetail(workingdayVo);
		
		return workingdayInfo;
	}
	
	public List<WorkVo> selectWorkInfo() {
		List<WorkVo> workList = wrkDayMapper.selectWorkInfo();
		
		return workList;
	}
	
	public List<SiteVo> selectSiteInfo() {
		List<SiteVo> siteList = wrkDayMapper.selectSiteInfo();
		
		return siteList;
	} 
}
