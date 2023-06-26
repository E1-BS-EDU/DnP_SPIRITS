package com.koko.dnpSpirits.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.koko.dnpSpirits.login.dto.UserVo;

@Mapper
public interface LoginMapper {
	void saveUser(UserVo userVo);	//회원가입
	
	int checkId(String userId);	    //ID check
	
	//회원탈퇴
}
