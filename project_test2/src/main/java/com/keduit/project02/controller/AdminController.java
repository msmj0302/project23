package com.keduit.project02.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keduit.project02.config.auth.PrincipalDetails;
import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.PriceVo;
import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.BookingRepository;
import com.keduit.project02.repository.PriceRepository;
import com.keduit.project02.repository.UserRepository;
import com.keduit.project02.service.BookingService;
import com.keduit.project02.service.UserService;

@Controller	// View를 리턴
public class AdminController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private UserService userService;
	
	
	
    @GetMapping("/admin/adminPage")
    public String adminPage(Model model,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	
    	if(principalDetail.getUser().getRole() == RoleType.ROLE_ADMIN) {
    		
    		List<User> userList = userRepository.findAll();
    		model.addAttribute("userList",userList);
    		
    		return "admin/adminPage";
    	}
    	
    	return "redirect:/user";
    }
    
    @GetMapping("/admin/adminPage2")
    public String adminPage2(Model model,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	
    	if(principalDetail.getUser().getRole() == RoleType.ROLE_ADMIN) {
    		
    		LocalDate now = LocalDate.now();
    		List<BookingVo> bookingList = bookingRepository.findPastBooking(now);
    		
    		model.addAttribute("bookingList",bookingList);
    		
    		return "admin/adminPage2";
    	}
    	
    	return "redirect:/user";
    }
    
    @GetMapping("/admin/adminPage3")
    public String adminPage3(Model model,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	
    	if(principalDetail.getUser().getRole() == RoleType.ROLE_ADMIN) {
    		
    		LocalDate now = LocalDate.now();
    		List<BookingVo> bookingList = bookingRepository.findBookingList(now);
    		
    		model.addAttribute("bookingList",bookingList);
    		
    		return "admin/adminPage3";
    	}
    	
    	return "redirect:/user";
    }
    
    @GetMapping("/admin/adminPage4")
    public String adminPage4(Model model,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	
    	if(principalDetail.getUser().getRole() == RoleType.ROLE_ADMIN) {
    		
    		List<PriceVo> priceVo = priceRepository.findAll();
    		
    		model.addAttribute("priceDetail",priceVo.get(0));
    		
    		return "admin/adminPage4";
    	}
    	
    	return "redirect:/user";
    }
    
    
    
    @PostMapping("/admin/roleModify/{username}")
    public String updateRole(@PathVariable("username") String username, User user,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	
    	if(principalDetail.getUser().getRole() == RoleType.ROLE_ADMIN) {
    		userService.userModify(user);
    		
    		return "redirect:/admin/adminPage";
    	}

    	return "redirect:/user";
    }
    
    @PostMapping("/admin/bookingDetail/{bno}")
    public String bookingDeatial(@PathVariable("bno") Long bno, BookingVo bookingVo,@AuthenticationPrincipal PrincipalDetails principalDetail, Model model) {
    	
    	if(principalDetail.getUser().getRole() == RoleType.ROLE_ADMIN) {
    		BookingVo bookingDetail = bookingRepository.findByBno(bno);
    		
    		model.addAttribute("bookingDetail", bookingDetail);
    		
    		return "admin/bookingDetailPage";
    	}
    	
    	return "redirect:/user";
    }
}
