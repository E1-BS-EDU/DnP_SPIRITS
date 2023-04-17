package com.koko.workweek.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koko.workweek.login.dto.UserVo;

@Mapper
public interface UserMapper {
	UserVo getUserAccount(String userId);	//로그인
	List<UserVo> getUserBirthSearch();	//생일 조회
}
