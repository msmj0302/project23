package com.keduit.project02.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.Criteria;
import com.keduit.project02.mapper.ReserMapper;

@Service
public class ReserServiceImpl implements ReserService {

	@Autowired
	private ReserMapper reserMapper;
	// 데이터베이스에 접근하는 DAO 선언(mapper사용함으로 DAO만들지않아도 됨)

	@Override
	public List<BookingVo> reserList(String username) throws Exception {

		return reserMapper.reserList(username);
	}// 사용자 요청을 처리하기 위한 로직 구현
	// 데이터를 조회하도록 reserMapper 클래스의 selectReserList 메소드 호출

	/*
	 * //페이징
	 * 
	 * @Override public List<BookingVo> listCriteria(Criteria cri,String username) {
	 * 
	 * return reserMapper.listCriteria(cri,username); }
	 * 
	 * @Override public int listCountCriteria(Criteria cri,String username) {
	 * 
	 * return reserMapper.listCountCriteria(cri,username); return 0; }
	 */

}
