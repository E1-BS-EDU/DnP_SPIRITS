package com.koko.workweek.kate.demo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoMapper {
	Map<String, Object>	selectData(Map<String ,Object> param) throws Exception;
}
