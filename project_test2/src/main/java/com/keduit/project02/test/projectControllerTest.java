package com.keduit.project02.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//특정 어노테이션이 붙어있는 클래스 파일들을 new해서 ioc 스프링 컨테이너에 관리
@RestController
public class projectControllerTest {

	//localhost:8089/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
