package com.koko.workweek.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {
	
	@Bean(name = "mybatisDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.maria")
	public DataSource mariaDataSource() {
		return DataSourceBuilder.create().build();	
	}
	
	// SqlSessionTemplate에서 사용할 SqlSession을 생성하는 Factory
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("mybatisDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		// MyBatis Config Setting MyBatis 설정 파일
		Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/config/mybatis-config.xml");
		sqlSessionFactoryBean.setConfigLocation(myBatisConfig);
		
		// MyBatis Mapper Source MyBatis의 SqlSession에서 불러올 쿼리 정보
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/sql/**/*.xml");		
		//sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
		sqlSessionFactoryBean.setMapperLocations(res);
		
		return sqlSessionFactoryBean.getObject();
	}

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
