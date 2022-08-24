package com.keduit.project02.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.CartVO;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.BookingRepository;
import com.keduit.project02.repository.CartRepository;

@Service
public class CartService2 {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Transactional
	public boolean cartInsert(CartVO cartVo) {
		
		String btype = ""+cartVo.getBtype();
		String bookingDate = ""+cartVo.getBookingDate();
    	ArrayList<String> disabledDayList = bookingService.findDisabledDays(cartVo.getBtype());
    	 for(int i=0; i<disabledDayList.size(); i++) {
    		 if(disabledDayList.get(i).equals(bookingDate)) {
    			 return false;
    			 
    		 }
    	 }
    	 
    	 
    	 cartRepository.save(cartVo);
    	 return true;
	}
	
	@Transactional
    @Modifying
    public boolean deleteCart(Long cno) {
		cartRepository.deleteByCno(cno);
    	return true;
    }
	
	@Transactional
	public boolean bookingInsertByCart(Long cno) {
		
		CartVO cartVo = cartRepository.findByCno(cno).orElseThrow(() -> {
            return new IllegalArgumentException("장바구니 데이터 찾기 실패");
        });
		String btype = ""+cartVo.getBtype();
		String bookingDate = ""+cartVo.getBookingDate();
    	ArrayList<String> disabledDayList = bookingService.findDisabledDays(cartVo.getBtype());
    	 for(int i=0; i<disabledDayList.size(); i++) {
    		 if(disabledDayList.get(i).equals(bookingDate)) {
    			 return false;
    			 
    		 }
    	 }
    	 BookingVo bookingVo = new BookingVo();
    	 bookingVo.setUsername(cartVo.getUsername());
    	 bookingVo.setName(cartVo.getName());
    	 bookingVo.setPhone(cartVo.getPhone());
    	 bookingVo.setDogSize(cartVo.getDogSize());
    	 bookingVo.setPictureSize(cartVo.getPictureSize());
    	 bookingVo.setPeople(cartVo.getPeople());
    	 bookingVo.setBookingDate(cartVo.getBookingDate());
    	 bookingVo.setPrice(cartVo.getPrice());
    	 bookingVo.setBtype(cartVo.getBtype());
    	 bookingVo.setThemeName(cartVo.getThemeName());
    	 bookingVo.setPrice(cartVo.getPrice());
    	 
    	 bookingRepository.save(bookingVo);
    	 deleteCart(cno);
    	 
    	 return true;
	}
	
}
