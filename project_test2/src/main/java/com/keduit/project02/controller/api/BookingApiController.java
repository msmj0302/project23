package com.keduit.project02.controller.api;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.project02.dto.ResponseDto;
import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.service.BookingService;

@RestController
public class BookingApiController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public boolean booking(@RequestBody BookingVo bookingVo){
		System.out.println("BookingApiController 호출됨 : booking 호출" + bookingVo.getBtype());
		
		boolean result = bookingService.insertBooking(bookingVo);
		
		return result;
	}
	

}