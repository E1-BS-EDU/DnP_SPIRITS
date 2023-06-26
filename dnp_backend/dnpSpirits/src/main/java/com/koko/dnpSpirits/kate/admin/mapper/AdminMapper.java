package com.koko.dnpSpirits.kate.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koko.dnpSpirits.kate.admin.dto.AdminVo;
import com.koko.dnpSpirits.kate.admin.dto.CommonCdVo;

@Mapper
public interface AdminMapper {
	/*관리자관리 목록 조회*/
	List<AdminVo> getAdminListMapper(AdminVo adminVo);	
	
	void adminListUpdateMapper(AdminVo adminVo);
	
	/*공통코드 목록 조회*/
	List<CommonCdVo> getCommonCdListMapper(int page);	
	
	/*상위코드 목록 조회*/
	List<CommonCdVo> getCommonCdMListMapper();	

	/*하위코드 상세 조회*/
	CommonCdVo getCommonCdDDetailMapper(String comCd);	
	
	/*공통코드 마스터 저장*/
	void commonCdMInsertMapper(List<CommonCdVo> commonListVo);
	
	/*공통코드 디테일 저장*/
	void commonCdDInsertMapper(CommonCdVo commonVo);
	
	/*공통코드 저장*/
	//void commonDetailSaveMapper(CommonCdVo commonVo);
	
	/*공통코드 디테일 수정*/
	void commonCdDUpdateMapper(CommonCdVo commonVo);
}
