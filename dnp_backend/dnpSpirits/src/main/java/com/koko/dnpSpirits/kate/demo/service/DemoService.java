package com.koko.dnpSpirits.kate.demo.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DemoService {
	ResponseEntity<?> getData(Map<String,Object> param) throws Exception;
}
