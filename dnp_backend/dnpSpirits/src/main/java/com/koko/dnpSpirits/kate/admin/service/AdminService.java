package com.koko.dnpSpirits.kate.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koko.dnpSpirits.kate.admin.dto.AdminVo;
import com.koko.dnpSpirits.kate.admin.dto.CommonCdVo;
import com.koko.dnpSpirits.kate.admin.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	
	/*관리자관리 리스트 조회 */
	public List<AdminVo> getAdminList(AdminVo adminVo) {
		
		List<AdminVo> adminList = adminMapper.getAdminListMapper(adminVo);
		
		if(adminList==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return adminList;
	}
	
	public void adminListUpdate(List<AdminVo> adminVoList) {
		for(AdminVo adminVo : adminVoList) {
			adminMapper.adminListUpdateMapper(adminVo);
		}
	}
	
	
	/*공통코드 리스트 조회 */
	public List<CommonCdVo> commonCdList(int page) {
		
		List<CommonCdVo> commonVo = adminMapper.getCommonCdListMapper(page);
		
		if(commonVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return commonVo;
	}
	
	/*상위코드 리스트 조회 */
	public List<CommonCdVo> commonCdMList() {
		
		List<CommonCdVo> commonVo = adminMapper.getCommonCdMListMapper();
		
		if(commonVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return commonVo;
	}
	
	
	/*하위코드 상세 조회 */
	public CommonCdVo commonCdDDetail(String comCd) {
		
		CommonCdVo commonVo = adminMapper.getCommonCdDDetailMapper(comCd);
		
		if(commonVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return commonVo;
	}
	
	/*공통코드 마스터 저장 */
	public void commonCdMInsert(List<CommonCdVo> commonListVo) {	
		adminMapper.commonCdMInsertMapper(commonListVo);
	}

	/*공통코드 마스터 수정 */
	public void commonCdMUpdate(CommonCdVo commonVo) {	
		//adminMapper.commonChildCdSaveMapper(commonVo);
	}

	/*공통코드 디테일 저장 */
	public void commonCdDInsert(CommonCdVo commonVo) {	
		adminMapper.commonCdDInsertMapper(commonVo);
	}


	/*공통코드 디테일 수정 */
	public void commonCdDUpdate(CommonCdVo commonVo) {	
		adminMapper.commonCdDUpdateMapper(commonVo);
	}

}
