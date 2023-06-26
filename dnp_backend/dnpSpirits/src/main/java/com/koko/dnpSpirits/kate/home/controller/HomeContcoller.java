package com.koko.dnpSpirits.kate.home.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.dnpSpirits.login.dto.SecurityUser;
import com.koko.dnpSpirits.login.dto.UserVo;
import com.koko.dnpSpirits.login.service.UserService;

@Controller
@RequestMapping(value = "/home")
public class HomeContcoller {

	Logger log = LoggerFactory.getLogger(HomeContcoller.class);
	
	@Autowired
    UserService userService;
	
	/**
     * 유저 페이지
     * @param model
     * @param authentication
     * @return
     */
    @GetMapping
    public String getHome(Model model, Authentication authentication ,HttpServletRequest req) {
    	log.debug("#### enter getHome");
    	
    	HttpSession session = req.getSession(false);
        if (session == null) {
            return "세션이 없습니다";
        }
    	
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        SecurityUser userVo = (SecurityUser) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userVo.getTeamName() +" "+userVo.getRealName() + " 로그인!!");      //유저 아이디
       
        
        //세션시간
        log.debug("sessionId = {}", session.getId());
        log.debug("getMaxInactiveInterval={}", session.getMaxInactiveInterval()); 
        log.debug("creationTime={}", new Date(session.getCreationTime()));
        log.debug("lastAccessTime={}", new Date(session.getLastAccessedTime()));
       
        System.out.println("#######:"+req.getRequestURL());
        return "kokoui/home/home";
    } 
    
    @GetMapping(path="/mainTabpage")
	public String mainTabpage() {
		return "kokoui/home/mainTabpage";
	}
	
    @ResponseBody
    @PostMapping(path="/birthSearch")
   	public List<UserVo> birthSearch() {
    	List<UserVo> userVo = userService.getUserBirth();
    	return userVo;
   	}
    
    @GetMapping(path="/adminTabpage")
  	public String adminTabpage() {
  		return "kokoui/home/adminTabpage";
  	}  
    
    
    @GetMapping(path="/common")
  	public String commonSearch(Model model) {
    	model.addAttribute("info","test"); 
    	System.out.println("####modeeeel###:"+model);
  		return null;
  	}  
}
