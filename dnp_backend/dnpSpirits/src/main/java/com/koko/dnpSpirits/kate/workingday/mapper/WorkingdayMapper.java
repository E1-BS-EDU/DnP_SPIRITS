package com.koko.dnpSpirits.kate.workingday.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.koko.dnpSpirits.kate.workingday.dto.SiteVo;
import com.koko.dnpSpirits.kate.workingday.dto.WorkVo;
import com.koko.dnpSpirits.kate.workingday.dto.WorkingdayVo;

@Mapper
public interface WorkingdayMapper {

	public int saveWorkingDay(List<WorkingdayVo> workingdayList);
	
	public int deleteWorkingDay(Map<String, Object> workingdayParam);
	
	public List<WorkingdayVo> selectWorkingDay(Map<String, Object> workingdayParam);
	
	public List<WorkingdayVo> selectCodeSample();
	
	public Map<String, Object> workingdayDetail(WorkingdayVo workingdayVo);
	
	public List<WorkVo> selectWorkInfo();
	
	public List<SiteVo> selectSiteInfo();
}
