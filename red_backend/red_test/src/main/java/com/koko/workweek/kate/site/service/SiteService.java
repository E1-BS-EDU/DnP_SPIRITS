package com.koko.workweek.kate.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koko.workweek.kate.site.dto.SiteVo;
import com.koko.workweek.kate.site.mapper.SiteMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SiteService {
	
	@Autowired
    SiteMapper siteMapper;

	public List<SiteVo> getSiteList() {
		List<SiteVo> siteVo = siteMapper.getSiteList();
		
		if(siteVo==null) {
			throw new UsernameNotFoundException("noticeList not found.");
		}
		
		return siteVo;
	}	
}
