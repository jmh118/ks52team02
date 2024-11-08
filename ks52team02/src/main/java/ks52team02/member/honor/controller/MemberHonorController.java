package ks52team02.member.honor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentor.service.MemberMentorService;
import ks52team02.member.review.mapper.MemberReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/honor")
public class MemberHonorController {

	private final MemberMentorService memberMentorService;
	private final MemberReviewMapper memberReviewMapper;
	
	@GetMapping("/mentorList")
	public String moveHonerMentorList(Model model) {
		System.out.println("멘토찾기 | 명예멘토 조회 화면");
		List<Member> honorMentorList = memberMentorService.getHonorMentorList();
		model.addAttribute("honorMentorList", honorMentorList);
		
		
		return  "member/mentorList/honoraryMentorList";
	}
}
