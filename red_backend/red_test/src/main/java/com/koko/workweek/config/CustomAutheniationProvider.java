package com.koko.workweek.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.koko.workweek.login.dto.SecurityUser;
import com.koko.workweek.login.service.UserService;

public class CustomAutheniationProvider implements AuthenticationProvider{
	         
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	Logger log = LoggerFactory.getLogger(CustomAutheniationProvider.class);
	 
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.debug("#### enter authenticate");
		
		String userId = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		SecurityUser userDetails = (SecurityUser) userService.loadUserByUsername(userId);
		
		// password 일치하지 않으면!
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("BadCredentialsException");
        }
        
        if(!userDetails.isAccountNonLocked()) {					//계정이 잠긴 경우(true로 고정, 추후 옵션처리 가능)
			throw new LockedException(userId);
		} else if(!userDetails.isEnabled()) {					//계정이 비활성화된 경우
			throw new DisabledException(userId);
		} else if(!userDetails.isAccountNonExpired()) {			//계정이 만료된 경우
			throw new AccountExpiredException(userId);
		} else if(!userDetails.isCredentialsNonExpired()) {		//비밀번호가 만료된 경우
			throw new CredentialsExpiredException(userId);
		}
               
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		return authenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
	