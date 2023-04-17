package com.koko.workweek.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.workweek.login.dto.UserVo;
import com.koko.workweek.login.service.LoginService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	LoginService authService;

	Logger log = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 회원가입 폼
	 * 
	 * @return
	 */
	@GetMapping("/signUp")
	public String signUpForm() {
		log.debug("Enter signUp");
		return "kokoui/login/signup";
	}

	/**
	 * 회원가입 진행
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/signUp")
	public String signUp(UserVo userVo) {
		try {
			log.debug("########## signUp");
			authService.joinUser(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "kokoui/login/login";
	}

	/**
	 * ID 중복체크
	 * rest api 식
	 * @param
	 * @return
	 */
	
	@ResponseBody
	@GetMapping("/checkId/{userId}")
	public Map<String, Object> checkId(@PathVariable("userId") String userId) {

		String rtn = "N";

		log.debug("########### checkId :: "+userId);
		int result = authService.checkId(userId);
		if (result > 0) {
			rtn = "Y";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rtn", rtn);
		
		System.out.println("checkId result ::  " + rtn);
		return map;
	}

}