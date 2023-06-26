package com.koko.dnpSpirits.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
basePackages = {
		"com.koko.dnpSpirits.config",
		"com.koko.dnpSpirits.kate",
		"com.koko.dnpSpirits.login"
})

@MapperScan(
basePackages = {
		"com.koko.dnpSpirits.*.mapper",
})

public class DnpSpiritsConfig {
}