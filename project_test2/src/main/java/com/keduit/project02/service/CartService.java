package com.keduit.project02.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keduit.project02.domain.CartVO;
import com.keduit.project02.domain.Criteria;
import com.keduit.project02.mapper.CartMapper;

import lombok.extern.log4j.Log4j;

@Service
public interface CartService {

	public List<Map<String, Object>> cartList() throws Exception;

	public Long register(CartVO vo); //register등록
	
	public CartVO get(Long cno); 
		
	public void delete(Long cno); //remove삭제

	List<CartVO> listCriteria(Criteria cri);

	public int listCountCriteria(Criteria cri);


	
	
	/*
	 * public CartVO readById(String id);
	 * 
	 * public void removeById(String id);
	 */

	 
}
