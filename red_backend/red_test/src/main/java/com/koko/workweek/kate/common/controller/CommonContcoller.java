package com.koko.workweek.kate.common.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koko.workweek.kate.admin.dto.CommonCdVo;
import com.koko.workweek.kate.common.service.CommonService;

@Controller
@RequestMapping(value = "/common")
public class CommonContcoller {
	@Autowired
	CommonService commonService;
	
	/*공통코드 리스트 조회 */
	@ResponseBody
    @PostMapping(path="/commonCdSearch")
	public List<CommonCdVo> commonCdSearch(String commonCdList) {
		List<String> list = (Arrays.asList(commonCdList.split(",")));
		List<CommonCdVo> commonCdVo = commonService.commonCdSearch(list);	
		return commonCdVo;
	}
	
}
