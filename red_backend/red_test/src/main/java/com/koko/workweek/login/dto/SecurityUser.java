package com.koko.workweek.login.dto;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	//security fields
	private Collection<? extends GrantedAuthority> authorities;
	private String username;	// credential(user_id)
	private String password;	// credential
	
	//domain fields(principal: 보호할 사용자 중요 데이터)
	private int userNo;
	private String userId;		//user_id
	private String RealName;	//user_name
	private String teamName;	//team_name
	private String vbgCreUserNo;
	private String vbgCreDtm;
	private String finCreUserNo;
	private String finCreDtm;
		
	public int getUserNo() {
		return userNo;
	}

	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getRealName() {
		return RealName;
	}

	public void setRealName(String RealName) {
		this.RealName = RealName;
	}
	
	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getVbgCreUserNo() {
		return vbgCreUserNo;
	}

	public void setVbgCreUserNo(String vbgCreUserNo) {
		this.vbgCreUserNo = vbgCreUserNo;
	}

	public String getVbgCreDtm() {
		return vbgCreDtm;
	}

	public void setVbgCreDtm(String vbgCreDtm) {
		this.vbgCreDtm = vbgCreDtm;
	}

	public String getFinCreUserNo() {
		return finCreUserNo;
	}

	public void setFinCreUserNo(String finCreUserNo) {
		this.finCreUserNo = finCreUserNo;
	}

	public String getFinCreDtm() {
		return finCreDtm;
	}

	public void setFinCreDtm(String finCreDtm) {
		this.finCreDtm = finCreDtm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecurityUser other = (SecurityUser) obj;
		return Objects.equals(userId, other.userId);
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE
        return authorities;
    }

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

	// 계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		// 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않았음
	}

	// 계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금되었는지 확인하는 로직
		return true; // true -> 잠금되지 않았음
	}

	// 패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		// 패스워드가 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않았음
	}

	// 계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		// 계정이 사용 가능한지 확인하는 로직
		return true; // true -> 사용 가능
	}
	
}