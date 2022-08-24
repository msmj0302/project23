package com.keduit.project02.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.project02.config.auth.PrincipalDetails;
import com.keduit.project02.dto.ResponseDto;
import com.keduit.project02.domain.Review;
import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.service.ReviewService;

@RestController
public class ReviewApiController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/api/review")
	public ResponseDto<Integer> save(@RequestBody Review review, @AuthenticationPrincipal PrincipalDetails principal) {
		reviewService.reviewCreate(review, principal.getUsername());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}