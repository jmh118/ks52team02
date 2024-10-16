package ks52team02.member.honor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/honor")
public class MemberHonorController {

	@GetMapping("/mentorList")
	public String moveHonerMentorList() {
		System.out.println("멘토찾기 | 명예멘토 조회 화면");
		return  "member/mentorList/honoraryMentorList";
	}
}
