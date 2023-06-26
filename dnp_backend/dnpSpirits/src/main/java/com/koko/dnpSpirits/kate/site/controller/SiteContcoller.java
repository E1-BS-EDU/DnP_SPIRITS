package com.koko.dnpSpirits.kate.site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.dnpSpirits.kate.site.dto.SiteVo;
import com.koko.dnpSpirits.kate.site.service.SiteService;

@Controller
@RequestMapping(value = "/site")
public class SiteContcoller {
	
	@Autowired
	SiteService siteService;
	
	@ResponseBody
    @PostMapping(path="/siteListSearch")
   	public List<SiteVo> siteListSearch() {
		System.out.println(120471204);
		List<SiteVo> siteVo = siteService.getSiteList();
   		return siteVo;
   	}
   
}
