package com.koko.dnpSpirits.login.dto;

public class UserRole {
	private int userId;
	private int roleId;
	private String roleName;
	private String appendDate;
	private String updateDate;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAppendDate() {
		return appendDate;
	}
	public void setAppendDate(String appendDate) {
		this.appendDate = appendDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}
