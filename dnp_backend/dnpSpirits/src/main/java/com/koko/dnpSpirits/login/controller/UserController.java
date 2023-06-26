package com.koko.dnpSpirits.login.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koko.dnpSpirits.login.dto.SecurityUser;
import com.koko.dnpSpirits.login.dto.UserVo;
import com.koko.dnpSpirits.login.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    UserService userService;
	
//	@GetMapping
//	public String index() {
//		return "kokoui/login/login";
//	}
	
	@GetMapping
	public String index(Model model, Authentication authentication) {
		System.out.println("############## 로그인 후 들어올때?");
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
		if(authentication==null) {
		}else {
				        
	        SecurityUser userVo = (SecurityUser) authentication.getPrincipal();  //userDetail 객체를 가져옴
	    	System.out.println("FFFFFFFFFFFFFFFFFFF"+userVo.getUsername());
	        model.addAttribute("info", userVo.getTeamName() +"의 "+userVo.getRealName() + "님");      //유저 아이디
		}
        return "kokoui/login/login";
	}
	
	@GetMapping(path="/duplogin")
	public String dublogin() {
		return "kokoui/login/duplogin";
	}
	
	@GetMapping(path="/access_denied")
	public String access_denied() {
		return "kokoui/login/login_denied";
	}   
	
	@GetMapping(path="accessDenied")
	public String accessDenied() {
		return "kokoui/login/login_denied";
	}  
	
	//http://localhost:8080/login/access_sso?sso=Y&user_id=fluei@naver.com
	@GetMapping(path="/access_sso")
	public String ssoLink(@RequestParam String sso, @RequestParam String user_id){
		
		log.debug("######## ENTER ssoLink");
		String path = "kokoui/login/login";
		
		if(sso ==null || user_id==null) {
		}else {
			if(sso.equals("Y")) {
				//user_id 트랜잭션 가져오기 구현
				
				//path="redirect:/kokoui/home/home";
				path="kokoui/home/home";
			}
		}

		UserVo userVo = userService.getUserInfo(user_id);
		SecurityUser userSecurity = new SecurityUser();
		
		userSecurity.setUsername(userVo.getUserId());
		userSecurity.setPassword(userVo.getUserPw());  // credetial
		userSecurity.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userVo.getUserAuth())));
		
        Authentication auth = new UsernamePasswordAuthenticationToken(userSecurity, null,Arrays.asList(new SimpleGrantedAuthority(userVo.getUserAuth())));
        SecurityContextHolder.getContext().setAuthentication(auth);
		
		log.debug("########### ssoLink return path :: "+path);
		
//		List<GrantedAuthority> roles = new ArrayList<>(1);
//		//String roleStr = username.equals("admin") ? "ROLE_ADMIN" : "ROLE_GUEST";
//		roles.add(new SimpleGrantedAuthority(userVo.getUserAuth()));
//		  
//
//		Authentication auth = new UsernamePasswordAuthenticationToken(userSecurity, null, roles);
//		SecurityContextHolder.getContext().setAuthentication(auth);
		  
		return path;
		
	}

}