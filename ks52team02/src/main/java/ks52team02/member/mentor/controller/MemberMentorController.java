package ks52team02.member.mentor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentor.service.MemberMentorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mentor")
@Controller
public class MemberMentorController {
	
	private final MemberMentorService memberMentorService;
	
	@GetMapping("/list") 
	public String moveMentorList(Model model) {
		System.out.println("멘토찾기 | 멘토 조회 화면");
		List<Member> mentorList = memberMentorService.getMentorList();
		model.addAttribute("mentorList", mentorList);
		
		return "member/mentorList/mentorList"; 
	}

}
