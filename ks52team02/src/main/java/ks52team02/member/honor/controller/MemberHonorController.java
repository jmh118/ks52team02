package ks52team02.member.honor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentor.service.MemberMentorService;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/honor")
public class MemberHonorController {

	private final MemberMentorService memberMentorService;
	
	@GetMapping("/mentorList")
	public String moveHonerMentorList(Pageable pageable, Model model, String keyId) {
		System.out.println("멘토찾기 | 명예멘토 조회 화면");
		PageInfo<Member> honorMentorList = memberMentorService.getHonorMentorList(pageable, keyId);
		model.addAttribute("honorMentorList", honorMentorList);
		
		return  "member/mentorList/honoraryMentorList";
	}
	
	@GetMapping("/mentorListIdSearch")
	public String mentorListIdSearch(@RequestParam(value = "keyId", required = false) String keyId,
									Pageable pageable, Model model) {
		System.out.println("멘토찾기 | 명예멘토 ID로 찾기");
		PageInfo<Member> memberPage = memberMentorService.getHonorMentorList(pageable, keyId);
		PageInfo<Member> honorMentorList = memberMentorService.getHonorMentorList(pageable, keyId);
		model.addAttribute("honorMentorList", memberPage.getContents());
		model.addAttribute("pageInfo", memberPage);
		model.addAttribute("honorMentorList", honorMentorList);
		
		return  "member/mentorList/honoraryMentorList";
	}
}
