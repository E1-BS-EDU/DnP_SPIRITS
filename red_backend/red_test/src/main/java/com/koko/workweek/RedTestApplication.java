package com.koko.workweek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy
public class RedTestApplication{

	Logger log = LoggerFactory.getLogger(RedTestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RedTestApplication.class, args);
	}

}
