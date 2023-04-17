package com.koko.workweek.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.koko.workweek.login.dto.UserVo;

@Mapper
public interface LoginMapper {
	void saveUser(UserVo userVo);	//회원가입
	
	int checkId(String userId);	    //ID check
	
	//회원탈퇴
}
