package com.keduit.project02.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.keduit.project02.config.auth.PrincipalDetails;
import com.keduit.project02.domain.BookingVo;
import com.keduit.project02.domain.Criteria;

import com.keduit.project02.domain.PageMaker;
import com.keduit.project02.service.ReserService;

@Controller // reserController 클래스를 컨트롤러로 작동
public class reserController {

	@Autowired
	private ReserService reserService;
	//  로직 처리하는 서비스 연결

	
	
	  @RequestMapping("/myPage/bookingList") //요청에 대한 주소 지정 public ModelAndView ModelAndView
	  ModelAndView reserList(@AuthenticationPrincipal PrincipalDetails principalDetail) throws Exception{ ModelAndView mv = new
	  ModelAndView("/myPage/bookingList"); //요청의 결과를 보여줄 뷰를 지정
	  
	  List<BookingVo>list = reserService.reserList(principalDetail.getUsername()); 
	  mv.addObject("list",list);
	  //실행된 로직의 결과 값을 뷰에 list라는 이름으로 저장
	  
	  return mv;
	  
	 }
	
	  
	
		/*
		 * @RequestMapping(value ="/reserPageList" ,method = RequestMethod.GET) public
		 * String reserPageList(@ModelAttribute("cri")Criteria cri ,Model
		 * model, @AuthenticationPrincipal PrincipalDetails principalDetail) throws
		 * Exception{
		 * 
		 * model.addAttribute("list",
		 * reserService.listCriteria(cri,principalDetail.getUsername())); PageMaker
		 * pageMaker = new PageMaker(); pageMaker.setCri(cri);
		 * pageMaker.setTotalCount(reserService.listCountCriteria(cri,principalDetail.
		 * getUsername()));
		 * 
		 * model.addAttribute("pageMaker",pageMaker);
		 * 
		 * return "reserList"; }
		 */
	 
	 

	

	
}
