package com.keduit.project02.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.BookingRepository;

@Service
public class BookingService {
	
	private int price = 30000;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public boolean insertBooking(BookingVo bookingVo) {
    	
    	String bookingDate = ""+bookingVo.getBookingDate();
    	ArrayList<String> disabledDayList = findDisabledDays(bookingVo.getBtype());
    	 for(int i=0; i<disabledDayList.size(); i++) {
    		 System.out.println("비활성화 날짜 : " + disabledDayList.get(i));
    		 System.out.println("예약하려는 날짜" + bookingVo.getBookingDate());
    		 if(disabledDayList.get(i).equals(bookingDate)) {
    			 return false;
    			 
    		 }
    	 }
    	 
    	 bookingRepository.save(bookingVo);
    	 return true;
    	
    }
    
    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public ArrayList<String> findDisabledDays(String btype) {
        
    	return bookingRepository.findDisabledDays(btype);
    	
    	
    }
    
    
    

}