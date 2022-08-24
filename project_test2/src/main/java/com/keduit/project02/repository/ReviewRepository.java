package com.keduit.project02.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.project02.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	void deleteByRno(Long rno);
	
	List<Review> findByUsername(String username);
	
	List<Review> findByBtype(String btype);
	
	Page<Review> findByUsername(Pageable pageable,String username);
	
}