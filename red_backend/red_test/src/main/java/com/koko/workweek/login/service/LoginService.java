package com.koko.workweek.login.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koko.workweek.login.dto.UserVo;
import com.koko.workweek.login.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService{
	
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");	// 회원가입 시 저장시간을 넣어줄 DateTime형
    Date time = new Date();
    String localTime = format.format(time);
	
	@Autowired
    LoginMapper loginMapper;

	public void joinUser(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String match="[^0-9]";
		userVo.setBirth(userVo.getBirth().replaceAll(match, ""));
		userVo.setPhone(userVo.getPhone().replaceAll(match, ""));
		userVo.setUserPw(passwordEncoder.encode(userVo.getUserPw()));
        userVo.setUserAuth("USER");
        
        loginMapper.saveUser(userVo);
	}
	
	public int checkId(String userId) {
		
		int rtn_int = loginMapper.checkId(userId);	
		return rtn_int;
	}
}
