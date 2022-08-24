package com.keduit.project02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keduit.project02.config.auth.PrincipalDetails;
import com.keduit.project02.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/myPage/review")
	public String Review() {
		return "/myPage/review";
	}

	@GetMapping("/myPage/myReview")
	public String myReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("reviews", reviewService.reviewListByUsername(pageable,principalDetail.getUsername()));
		return "/myPage/myReview";
	}

	@GetMapping("/swimSpa/spa")
	public String spaReview(Model model,
			@PageableDefault(size = 7, sort = "rno", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("reviews", reviewService.reviewList(pageable));
		return "/swimSpa/spa";
	}
	
	@PostMapping("/myPage/myReview/{rno}")
	public String reviewDelete(@PathVariable Long rno) {
//		Long rno = Long.parseLong(rnoValue);
		reviewService.reviewDelete(rno);
		return "redirect:/myPage/myReview";
	}
}