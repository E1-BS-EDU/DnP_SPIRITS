package com.koko.dnpSpirits.kate.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koko.dnpSpirits.kate.common.interceptor.CmmInterceptor;

@Configuration
public class CmmConfig implements WebMvcConfigurer  {

	@Autowired
	private CmmInterceptor cmmInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(cmmInterceptor)
        .addPathPatterns("/*")
        .excludePathPatterns(
        		"/",
        		"/login/*"
        		); // 해당 경로는 인터셉터가 가로채지 않는다.
        
        ;
	
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
}
