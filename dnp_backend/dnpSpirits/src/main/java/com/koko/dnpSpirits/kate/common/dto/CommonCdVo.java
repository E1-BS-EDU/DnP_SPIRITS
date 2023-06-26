package com.koko.dnpSpirits.kate.common.dto;

import java.util.List;

public class CommonCdVo {

	private String comCd;
	private String comNm;
	private String etc;
	private String etcDesc;
	private int vbgCreUserNo;
	private String vbgCreDtm;
	private int finCreUserNo;
	private String finCreDtm;
	private String useYn;
	
	//etc
	private List<CommonCdVo> commonList;
	private String parentCd; // 부모 컬럼 cd 
	private String parentNm; // 부모 컬럼 nm

	public List<CommonCdVo> getCommonList() {
		return commonList;
	}

	public void setCommonList(List<CommonCdVo> commonList) {
		this.commonList = commonList;
	}


	public String getComCd() {
		return comCd;
	}
	public void setComCd(String comCd) {
		this.comCd = comCd;
	}
	public String getComNm() {
		return comNm;
	}
	public void setComNm(String comNm) {
		this.comNm = comNm;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getEtcDesc() {
		return etcDesc;
	}
	public void setEtcDesc(String etcDesc) {
		this.etcDesc = etcDesc;
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
	public String getParentCd() {
		return parentCd;
	}
	public void setParentCd(String parentCd) {
		this.parentCd = parentCd;
	}
	public String getParentNm() {
		return parentNm;
	}
	public void setParentNm(String parentNm) {
		this.parentNm = parentNm;
	}
	
}