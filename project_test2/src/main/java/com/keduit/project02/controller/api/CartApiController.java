package com.keduit.project02.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.keduit.project02.dto.ResponseDto;
import com.keduit.project02.repository.CartRepository;
import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.CartVO;
import com.keduit.project02.service.BookingService;
import com.keduit.project02.service.CartService2;

@RestController
public class CartApiController {

	@Autowired
	private CartService2 cartService2;
	
	
	
	@PostMapping("/cart")
	public boolean cart(@RequestBody CartVO cartVo){
		System.out.println("BookingApiController 호출됨 : booking 호출" + cartVo.getBtype());
		
		boolean result = cartService2.cartInsert(cartVo);
		
		return result;
	}
	
	@RequestMapping(value = "/cartDelete")
	public boolean cartDelete(HttpServletRequest request) {
		System.out.println("CartApiController 호출됨 : booking 호출");
		String[] ajaxMsg = request.getParameterValues("valueArr");
		Long[] cnoList = new Long[ajaxMsg.length];
		for(int i=0; i<ajaxMsg.length; i++) {
			System.out.println("CartApiController 호출됨 : booking 호출" +ajaxMsg[i]);
			cnoList[i]=Long.parseLong(ajaxMsg[i]);
			System.out.println("Long 타입 변환" + cnoList[i]);
		}
		boolean result = true;
		for(int i=0; i<cnoList.length; i++) {
			boolean delete = cartService2.deleteCart(cnoList[i]);
			if(delete==false) {
				result = false;
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/cartSave")
	public boolean bookingByCart(HttpServletRequest request){
		System.out.println("BookingApiController 호출됨 : booking 호출");
		String[] ajaxMsg = request.getParameterValues("valueArr");
		Long[] cnoList = new Long[ajaxMsg.length];
		for(int i=0; i<ajaxMsg.length; i++) {
			System.out.println("CartApiController 호출됨 : booking 호출" +ajaxMsg[i]);
			cnoList[i]=Long.parseLong(ajaxMsg[i]);
			System.out.println("Long 타입 변환" + cnoList[i]);
		}
		
		boolean result = true;
		for(int i=0; i<cnoList.length; i++) {
			result = cartService2.bookingInsertByCart(cnoList[i]);
			
		}
		
		return result;
	}
	
	

}