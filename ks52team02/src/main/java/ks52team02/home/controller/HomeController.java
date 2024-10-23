package ks52team02.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String indexMove() {
		return "/index";
	}
	
	@GetMapping("/member")
	public String MemberPageMove() {
		return "member/memberMain";
	}
	
	@GetMapping("/manager")
    public String managerPageMove() {
        return  "manager/managerMain";
    }

}
