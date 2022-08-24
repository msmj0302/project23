package com.keduit.project02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.project02.domain.*;
import com.keduit.project02.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
	public void reviewCreate(Review review, String username) {
		review.setBtype("스파");
		review.setScope(review.getScope());
		review.setUsername(username);
		reviewRepository.save(review);
	}

	@Transactional(readOnly = true)
	public Page<Review> reviewList(Pageable pageable) {
		return reviewRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Page<Review> reviewListByUsername(Pageable pageable,String username) {
		return reviewRepository.findByUsername(pageable,username);
	}

	@Transactional(readOnly = true)
	public List<Review> myReviewList(String username) {
		return reviewRepository.findByUsername(username);
	}
	@Transactional(readOnly = true)
	public List<Review> reviewListByBtype(String btype) {
		return reviewRepository.findByBtype(btype);
	}

	@Transactional
	@Modifying
	public void reviewDelete(Long rno) {
		reviewRepository.deleteByRno(rno);
		System.out.println("글 삭제하기 : " + rno);
	}
}