package com.koko.dnpSpirits.kate.notice.dto;

public class NoticeVo {

	private int no;
	private String title;
	private String content;
	private int vbgCreUserNo;
	private String vbgCreDtm;
	private int finCreUserNo;
	private String finCreDtm;
	private String useYn;
	
	//etc
	private String userName; // 사용자 이름 
	private String totalCnt; // 공시사항 전체 글 수
	private String gubun;  //저장save 수정update 
	

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getVbgCreUserNo() {
		return vbgCreUserNo;
	}
	public void setVbgCreUserNo(int vbgCreUserNo) {
		this.vbgCreUserNo = vbgCreUserNo;
	}
	public String getVbgCreDtm() {
		return vbgCreDtm;
	}
	public void setVbgCreDtm(String vbgCreDtm) {
		this.vbgCreDtm = vbgCreDtm;
	}
	public int getFinCreUserNo() {
		return finCreUserNo;
	}
	public void setFinCreUserNo(int finCreUserNo) {
		this.finCreUserNo = finCreUserNo;
	}
	public String getFinCreDtm() {
		return finCreDtm;
	}
	public void setFinCreDtm(String finCreDtm) {
		this.finCreDtm = finCreDtm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	//etc
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

}