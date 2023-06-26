package com.koko.dnpSpirits.kate.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	
	@GetMapping(path="/demoHome")
	public String hello() {
		return "kokoui/demo/demoHome";
	}
	
	@GetMapping(path="/demoBoard")
	public String demoBoard() {
		return "kokoui/demo/demoBoard";
	}
	
	@GetMapping(path="/demoWorkingDay")
	public String demoWorkingDay() {
		return "kokoui/demo/demoWorkingDay";
	}

}
