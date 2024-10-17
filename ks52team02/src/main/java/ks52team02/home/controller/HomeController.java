package ks52team02.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ks52team02.manager.member.dto.Member;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String moveIndex() {
		System.out.println("사용자 메인 화면");
		return "member/index";
	}
	
	@GetMapping("/manager")
    public String managerPageMove() {
    	System.out.println("관리자 메인 화면");
        return  "manager/managerMain";
    }

}
