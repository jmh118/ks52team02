package ks52team02.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String indexMove() {
		return "/index";
	}
	
	@GetMapping(value = {"/member","/member/"})
	public String MemberPageMove(HttpSession session) {
		return "member/memberMain";
	}
	
	@GetMapping("/manager")
    public String managerPageMove() {
        return  "manager/managerMain";
    }

}
