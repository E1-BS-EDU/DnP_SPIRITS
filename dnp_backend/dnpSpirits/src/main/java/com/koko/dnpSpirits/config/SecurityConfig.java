package com.koko.dnpSpirits.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@SuppressWarnings("deprecation")
@EnableWebSecurity(debug = false)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 // 정적인 파일에 대한 요청들
    private static final String[] AUTH_WHITELIST = {	
            // -- swagger ui
            "/css/**",
            "/js/**",
            "/img/**",
            "/xml/**",
            "/webjars/**",
    };
    
	// 스프링 시큐리티의 필터 연결을 설정하기 위한 오버라이딩이다.
    //예외가 웹접근 URL를 설정한다.
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);		 // 정적인 파일 요청에 대해 무시
    }
	
	// 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위한 오버라이딩이다.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		http.authorizeRequests()
//		// login 없이 접근 허용 하는 url
//		.antMatchers("/" ,"/login/**").permitAll()
//		.antMatchers("/home").hasRole("ROLE_USER")         // USER, ADMIN만 접근 가능
////		.antMatchers("/cago/**").hasRole("USER")	  // USER, ADMIN만 접근 가능
////		.antMatchers("/admin/**").hasRole("ADMIN") // USER, ADMIN만 접근 가능
//		// '/admin'의 경우 ADMIN 권한이 있는 사용자만 접근이 가능
//		//.antMatchers("/admin").hasRole("ADMIN")
//		.anyRequest().authenticated()	// 그 외 모든 요청은 인증과정 필요
//		//.anyRequest().denyAll()	//그 외 모든 요청 거절
//		.and()
//			.formLogin()
//				.loginPage("/login")					// 로그인 페이지 링크
//				.loginProcessingUrl("/login_proc")
//				.defaultSuccessUrl("/home")
//				//.failureUrl("/login?error=true") 		// 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
//				.failureUrl("/access_denied") 		// 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
//				
//				
//		.and()
//			.logout()
//				//.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//		        //.logoutSuccessUrl("/") // 로그아웃 성공시
//				.logoutSuccessUrl("/login")
//				.invalidateHttpSession(true) //세션 날리기
//		
		//1. ACL 설정
		http.csrf().disable();
		http.authorizeRequests()
		// login 없이 접근 허용 하는 url
		.antMatchers("/" ,"/login/**").permitAll()
		.antMatchers("/demo/**").permitAll()
		//.antMatchers("/home/**").hasRole("USER")         // hasRole 을 쓸려면 dB에서 권한 가져올때 ROLE_USER 이런식으로 가져와야 hasRole에서 USER 사용가능
		.antMatchers("/home/**").hasAuthority("USER")	   // USER, ADMIN만 접근 가능
		.antMatchers("/notice/**").hasAuthority("USER")	
		.antMatchers("/workweek/**").hasAuthority("USER")	
		.antMatchers("/workingday/**").hasAuthority("USER")
		.antMatchers("/site/**").hasAuthority("USER")
		.antMatchers("/common/**").hasAuthority("USER")
		.antMatchers("/admin/**").hasAuthority("USER")
		.anyRequest().denyAll()	//그 외 모든 요청 거절
		//.anyRequest().permitAll();
		;
		//2. 로그인 설정
		
		http
			.formLogin()
			.loginPage("/login")					// 로그인 페이지 링크
			.loginProcessingUrl("/login_proc")
			.defaultSuccessUrl("/home" ,false)	    //만약 사용자가  로그인 페이지로 직접 접근하는 것이 아니라 접근 권한이 없는 경로로 이동중에 사이트가 로그인을 요구할때 
			                                        //로그인에 성공한다면 사용자가 원래 가려던 페이지로 보내줘야하는데  defaultSuccessUrl의 alwayUse를 true로 하면 
													//항상 defaultSuccessUrl로 지정한 Url로 보내버리기 떄문에 alwaysUse는 false로 지정
			
			//.failureUrl("/login?error=true") 		// 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
			.failureUrl("/access_denied") 			// 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
		;

		http
			.logout()
				.logoutUrl("/doLogout")
				.logoutSuccessUrl("/login")		// 로그아웃 성공시
				.invalidateHttpSession(true) 	//세션 날리기
				.deleteCookies("JSESSIONED")	//쿠키 제거
				.clearAuthentication(true)		//권한정보 제거
		;
		//인증은 되었으나 접근권한 없는 경우.
		http
			.exceptionHandling()
			.accessDeniedPage("/accessDenied")
		;
		
		//iframe 문제 
		
		http
			.headers().frameOptions().sameOrigin();
		
		//세션처리
		
		http
			.sessionManagement()
			//.sessionFixation().changeSessionId()    //세션 고정보호
			.maximumSessions(1)						//세션 허용갯수 . 중복로그인 차단시 1
			
			.maxSessionsPreventsLogin(false) 	    //동일한 사용자 로그인시 x, 
													//true  :  나중에 로그인 한 사용자 차단
		                                            //false :  먼저 로그인 한 사용자 로그아웃 처리
		    .expiredUrl("/login")
			.sessionRegistry(sessionRegistry())
			
			;

	}
	
	// 사용자 세부 서비스를 설정하기 위한 오버라이딩이다.
	/**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAutheniationProvider();
    }
    
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
    
	// logout 후 login할 때 정상동작을 위함
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

    // was가 여러개 있을 때(session clustering)
    @Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
	
    /***
     *  외부 was 쓸경우 web.xml에 등록 
     * 
     * <listener>
    	<listener-class>org.springframework.security.web.session.HttpSessionEventPublisher</listener-class>
		</listener>
     * 
     * 
     */

}
