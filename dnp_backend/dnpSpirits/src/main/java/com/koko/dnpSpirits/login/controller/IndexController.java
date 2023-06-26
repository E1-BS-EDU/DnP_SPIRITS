package com.koko.dnpSpirits.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	/**
     * 유저 페이지
     * @param model
     * @param authentication
     * @return
     */
	@GetMapping
	public String index() {
		return "kokoui/login/login";
	}
}
