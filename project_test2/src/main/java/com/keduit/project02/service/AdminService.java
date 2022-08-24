package com.keduit.project02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.project02.domain.PriceVo;
import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.PriceRepository;
import com.keduit.project02.repository.UserRepository;

@Service
public class AdminService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder encode;
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	public AdminService(UserRepository userRepository, BCryptPasswordEncoder encode,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.encode = encode;
		this.authenticationManager = authenticationManager;
	}
	
	
	@Transactional
	public String priceModify(PriceVo priceVo,User user) {
		
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정한다.
        // SELECT를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화하기 위해서이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		
		System.out.println(priceVo.getPno());
		System.out.println(priceVo.getConPrice());
        PriceVo persistance = priceRepository.findByPno(priceVo.getPno()).orElseThrow(() -> {
            return new IllegalArgumentException("가격 찾기 실패");
        });

        if(user.getRole() == RoleType.ROLE_ADMIN) {
        	persistance.setConPrice(priceVo.getConPrice());
        	persistance.setDogNumPrice(priceVo.getDogNumPrice());
        	persistance.setDogSizePrice(priceVo.getDogSizePrice());
        	persistance.setFamilyPrice(priceVo.getFamilyPrice());
        	persistance.setIdPrice(priceVo.getIdPrice());
        	persistance.setPeopleNumPrice(priceVo.getPeopleNumPrice());
        	persistance.setPictureSizePrice(priceVo.getPictureSizePrice());
        	persistance.setSpaPrice(priceVo.getSpaPrice());
        	persistance.setSwimPrice(priceVo.getSwimPrice());
        	
        	return "가격 변경이 적용되었습니다.";
        }
        
        return "가격 변경에 실패하였습니다(권한 없음).";
		
	}
	
    
    

}