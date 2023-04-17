package com.koko.workweek.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
basePackages = {
		"com.koko.workweek.config",
		"com.koko.workweek.kate",
		"com.koko.workweek.login"
})

@MapperScan(
basePackages = {
		"com.koko.workweek.*.mapper",
})

public class RndTestConfig {
}