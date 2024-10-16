package ks52team02.member.mentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mentor")
public class MemberMentorController {
	
	
	@GetMapping("/list") 
	public String moveMentorList() {
		System.out.println("멘토찾기 | 멘토 조회 화면"); 
		return "member/mentorList/mentorList"; 
	}

}
