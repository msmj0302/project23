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
import com.keduit.project02.domain.CartVO;
import com.keduit.project02.domain.PriceVo;
import com.keduit.project02.domain.Review;
import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.BookingRepository;
import com.keduit.project02.repository.CartRepository;
import com.keduit.project02.repository.PriceRepository;
import com.keduit.project02.repository.UserRepository;
import com.keduit.project02.service.BookingService;
import com.keduit.project02.service.CartService;
import com.keduit.project02.service.UserService;

@Controller	// View를 리턴
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//localhost:8080/
	//localhost:8080/
	@GetMapping({"","/"})
	public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("user", principalDetail.getUser());
		
		return "index";	//src/main/resources/templates/index.mustache
	}
	@GetMapping("/photo/{url}")
	public String photo(@PathVariable String url,Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("user", principalDetail.getUser());
		
		return "photo/"+url;	//src/main/resources/templates/index.mustache
	}
	@GetMapping("/swimSpa/{url}")
	public String swimSpa(@PathVariable String url,Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("user", principalDetail.getUser());
		String btype = "스파";
		
		return "swimSpa/"+url;	//src/main/resources/templates/index.mustache
	}
	@GetMapping("/food/{url}")
	public String food(@PathVariable String url,Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("user", principalDetail.getUser());
		
		return "food/"+url;	//src/main/resources/templates/index.mustache
	}
	@GetMapping("/info/{url}")
	public String info(@PathVariable String url,Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("user", principalDetail.getUser());
		
		return "info/"+url;	//src/main/resources/templates/index.mustache
	}
	@GetMapping("/myPage/{url}")
	public String myPage(@PathVariable String url,Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		model.addAttribute("user", principalDetail.getUser());
		
		return "myPage/"+url;	//src/main/resources/templates/index.mustache
	}
	/*
	 * @GetMapping("/myPage/{url}") public String myPage(@PathVariable String
	 * url,Model model) {
	 * 
	 * return "myPage/"+url; //src/main/resources/templates/index.mustache }
	 */
	
	@GetMapping("/user")
	public String user(Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		
		model.addAttribute("user", principalDetail.getUser());
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// 스프링시큐리티 해당주소를 낚아챔
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	
	
    @GetMapping("/updateForm")
    public String updateForm(Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
        model.addAttribute("user", principalDetail.getUser());
        return "updateForm";
    }
	

    @GetMapping("/reservation/photo/{url}")
    public String photoReservationPage(@PathVariable String url,Model model,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	ArrayList<String> disabledDayList = bookingService.findDisabledDays("포토");	
    	
    	List<PriceVo> priceVo = priceRepository.findAll();
    	model.addAttribute("user", principalDetail.getUser());
    	model.addAttribute("disabledDayList",disabledDayList);
    	model.addAttribute("price",priceVo.get(0));
		
    	return "reservation/photo/"+url;
    }
    @GetMapping("/reservation/spaSwim/{url}")
    public String spaPoolReservationPage(@PathVariable String url,Model model,@AuthenticationPrincipal PrincipalDetails principalDetail) {
    	String btype;
    	if(url.equals("spareserPage")) {
    		btype="스파";
    	}else {
    		btype="수영장";
    	}
    	ArrayList<String> disabledDayList = bookingService.findDisabledDays(btype);	
    	
    	List<PriceVo> priceVo = priceRepository.findAll();
    	model.addAttribute("user", principalDetail.getUser());
    	model.addAttribute("disabledDayList",disabledDayList);
    	model.addAttribute("price",priceVo.get(0));
    	return "reservation/spaSwim/"+url;
    }

    @GetMapping("/deleteForm")
    public String deleteForm() {
    	return "deleteForm";
    }
    
	@GetMapping("/cartList")
	public String showReserList(Model model, @AuthenticationPrincipal PrincipalDetails principalDetail) {
		System.out.println("cartlist 호출됨");
		List<CartVO> cartList = cartRepository.findByUsername(principalDetail.getUsername());
		System.out.println(cartList.get(0).getCno());
		model.addAttribute("cartList", cartList);

		return "cartList";
	}
	
   

}
