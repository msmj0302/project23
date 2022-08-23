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

import com.keduit.project02.domain.RoleType;
import com.keduit.project02.domain.User;
import com.keduit.project02.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder encode;
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder encode,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.encode = encode;
		this.authenticationManager = authenticationManager;
	}
	
    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public void userCreate(User user) {
        String rawPassword = user.getPassword(); // 원본
        String encPassword = encode.encode(rawPassword); // 해쉬 비밀번호
        user.setPassword(encPassword);
        user.setRole(RoleType.ROLE_USER);
        userRepository.save(user);
    }
	
	@Transactional
	public void userModify(User user) {
		
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정한다.
        // SELECT를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화하기 위해서이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.

        User persistance = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

        if(user.getRole() != null) {
        	persistance.setRole(user.getRole());
        }else {
        	
        	String rawPassword = user.getPassword();
        	String encPassword = encode.encode(rawPassword);
        	persistance.setPassword(encPassword);
        	persistance.setEmail(user.getEmail());
        	persistance.setUserTel(user.getUserTel());
        	persistance.setDogName(user.getDogName());
        	persistance.setDogType(user.getDogType());
        	persistance.setName(user.getName());
        	
        	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        	SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        



        // 회원수정 함수 종료시 == 서비스 종료 == 트랜잭션 종료 == commit이 자동으로 됨
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 변경된 것들을 update해준다.
		
		//세션 등록
		
	}
	
    public boolean getUsernameCheck(String username) {
        if((userRepository.findByUsername(username)) !=null ){
             System.out.println("아이디 체크 성공");
             return true;
        }
        return false;
    }
    
    @Transactional
    @Modifying
    public boolean deletUser(String password,User user) {
    	
    	System.out.println("비번"+password);
    	System.out.println(user.getPassword());
    	if(userRepository.findByUsername(user.getUsername()) !=null) {
    		if(encode.matches(password, user.getPassword())) {
    			
    			userRepository.deleteByUsername(user.getUsername());
    			return true;
    		}
    		return false;
    	}
    	return false;
    }
    
    @Transactional
    public boolean confirmUsername(String username) {
    	
    	System.out.println(username);
    	System.out.println(userRepository.existsByUsername(username));
    	return userRepository.existsByUsername(username);
    }
    

}
