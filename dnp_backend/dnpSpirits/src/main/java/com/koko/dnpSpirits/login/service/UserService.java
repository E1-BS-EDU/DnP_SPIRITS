package com.koko.dnpSpirits.login.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koko.dnpSpirits.login.dto.SecurityUser;
import com.koko.dnpSpirits.login.dto.UserVo;
import com.koko.dnpSpirits.login.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService  {
	
	Logger log = LoggerFactory.getLogger(UserService.class);
	
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:sss");	// 회원가입 시 저장시간을 넣어줄 DateTime형
    Date time = new Date();
    String localTime = format.format(time);
	
	@Autowired
    UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.debug("#####loadUserByUsername##### :: "+username);

		UserVo userVo = userMapper.getUserAccount(username);    //사용자 정보
		
		if(userVo==null) {
			throw new UsernameNotFoundException("User not authorized.");
		}
		
		SecurityUser userSecurity = new SecurityUser();
		
		
		userSecurity.setUsername(userVo.getUserId());
		userSecurity.setPassword(userVo.getUserPw());  // credetial
		
		System.out.println("#### :: "+userVo.getUserPw());
		System.out.println("#### :: "+userSecurity.getPassword());
		
        userSecurity.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userVo.getUserAuth())));
		//userSecurity.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("USER")));
		
//		List<GrantedAuthority> authorities =  new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(userVo.getRole()));
//
//		userSecurity.setAuthorities(authorities);

        userSecurity.setUserNo(userVo.getUserNo());
        userSecurity.setRealName(userVo.getUserName());
        userSecurity.setTeamName(userVo.getTeamName());
        userSecurity.setVbgCreDtm(userVo.getVbgCreDtm());
        userSecurity.setVbgCreUserNo(userVo.getVbgCreUserNo());
        userSecurity.setFinCreDtm(userVo.getFinCreDtm());
        userSecurity.setFinCreUserNo(userVo.getFinCreUserNo());
     
		return userSecurity;
	}
	
	public UserVo getUserInfo(String user_id) {
		
		UserVo userVo = userMapper.getUserAccount(user_id);
		
		if(userVo==null) {
			throw new UsernameNotFoundException("User id not found.");
		}
		
		return userVo;
	}

	
	public List<UserVo> getUserBirth() {
		
		List<UserVo> userVo = userMapper.getUserBirthSearch();
		
		if(userVo==null) {
			throw new UsernameNotFoundException("User id not found.");
		}
		
		return userVo;
	}
}
