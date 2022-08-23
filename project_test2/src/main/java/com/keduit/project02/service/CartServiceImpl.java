package com.keduit.project02.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keduit.project02.domain.CartVO;
import com.keduit.project02.domain.Criteria;
import com.keduit.project02.mapper.CartMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor

public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper mapper;
	

	@Override
	public List<Map<String, Object>> cartList() throws Exception {

		return mapper.cartList();
	}

	@Transactional
	@Override
	public Long register(CartVO vo) {
		// 장바구니 등록
	    // 확인필요 
		/* CartMapper.updateCartCnt(vo.getCno(), 1); */
		return mapper.insert(vo);
	}


	@Override
	public CartVO get(Long cno) {
		// 장바구니 상세조회 -> 입력정보?
		return mapper.read(cno);
	}
	



	@Override
	public void delete(Long cno) {

		mapper.delete(cno);
	}

	@Override
	   public List<CartVO> listCriteria(Criteria cri) {
	      
	      return mapper.listCriteria(cri);
	   }

	   @Override
	   public int listCountCriteria(Criteria cri) {
	      
	      return 0;
	   }

	
	
	
	/*
	 * @Override public CartVO readById(String id) { return mapper.readById(id); }
	 * 
	 * @Override public void removeById(String id) { }
	 */
}
