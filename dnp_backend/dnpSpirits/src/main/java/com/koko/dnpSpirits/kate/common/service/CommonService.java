package com.koko.dnpSpirits.kate.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koko.dnpSpirits.kate.admin.dto.CommonCdVo;
import com.koko.dnpSpirits.kate.common.mapper.CommonMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommonService {
	
	@Autowired
    CommonMapper commonMapper;
	
	
	/*공통코드 리스트 조회 */
	public List<CommonCdVo> commonCdSearch(List<String> commonCdList) {

		List<CommonCdVo> commonVo = commonMapper.getcommonCdSearchMapper(commonCdList);

		if(commonVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return commonVo;
	}
	
}
