package com.keduit.project02.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.Criteria;
import com.keduit.project02.domain.PageMaker;
import com.keduit.project02.mapper.ReserMapper;

@Service //로직을 처리하는 서비스 클래스
public interface ReserService { //로직을 수행하기 위한 메소드 정의

	List<BookingVo> reserList(String username) throws Exception;

	/*
	 * List<BookingVo> listCriteria(Criteria cri,String username);
	 * 
	 * int listCountCriteria(Criteria cri, String username);
	 */
	
	
	
	
}
