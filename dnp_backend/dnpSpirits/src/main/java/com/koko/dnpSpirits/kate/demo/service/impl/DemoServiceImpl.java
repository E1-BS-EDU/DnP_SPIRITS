package com.koko.dnpSpirits.kate.demo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.koko.dnpSpirits.kate.demo.mapper.DemoMapper;
import com.koko.dnpSpirits.kate.demo.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoMapper demoMapper;

	@Override
	public ResponseEntity<?> getData(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		Map<String ,Object> retval = demoMapper.selectData(param);
		
		return ResponseEntity.ok().body(retval);
	}
	
	
	
}
