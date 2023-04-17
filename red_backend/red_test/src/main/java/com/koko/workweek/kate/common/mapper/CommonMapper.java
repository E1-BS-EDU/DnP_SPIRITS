package com.koko.workweek.kate.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koko.workweek.kate.admin.dto.CommonCdVo;

@Mapper
public interface CommonMapper {
	
	/*공통코드 목록 조회*/
	List<CommonCdVo> getcommonCdSearchMapper(List<String> commonCdList);	
	
}
