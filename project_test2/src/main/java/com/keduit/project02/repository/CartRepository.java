package com.keduit.project02.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keduit.project02.domain.CartVO;
import com.keduit.project02.domain.User;

public interface CartRepository extends JpaRepository<CartVO, Integer>{

	
	List<CartVO> findByUsername(String username);
	
	Optional<CartVO> findByCno(Long cno);
	
	void deleteByCno(Long cno);
	
}
