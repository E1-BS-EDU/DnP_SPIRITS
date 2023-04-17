package com.koko.workweek.kate.workingday.dto;

import java.sql.Date;

public class WorkingdayVo {
	private Date workDate;
	private int userNo;
	private int gubunCd;
	private int id;
	private int siteNo;
	
	private String vbgCreUserNo;
	private String vbgCreDtm;
	private String finCreUserNo;
	private String finCreDtm;
	
	private String gubunNm;
	private String siteNm;
	
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getGubunCd() {
		return gubunCd;
	}
	public void setGubunCd(int gubunCd) {
		this.gubunCd = gubunCd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSiteNo() {
		return siteNo;
	}
	public void setSiteNo(int siteNo) {
		this.siteNo = siteNo;
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
	public String getGubunNm() {
		return gubunNm;
	}
	public void setGubunNm(String gubunNm) {
		this.gubunNm = gubunNm;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	
	
	
}
