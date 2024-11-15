package ks52team02.member.mentor.controller;

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
@RequestMapping("/mentor")
@Controller
public class MemberMentorController {
	
	private final MemberMentorService memberMentorService;
	
	@GetMapping("/list") 
	public String moveMentorList(Pageable pageable, Model model, String keyId) {
		System.out.println("멘토찾기 | 멘토 조회 화면");
		PageInfo<Member> mentorList = memberMentorService.getMentorList(pageable, keyId);
		model.addAttribute("mentorList", mentorList);
		
		return "member/mentorList/mentorList"; 
	}
	
	@GetMapping("/listMentorIdSearch") 
	public String listMentorIdSearch(@RequestParam(value = "keyId", required = false) String keyId,
            						Pageable pageable, Model model) {
		System.out.println("멘토찾기 | 멘토 조회 화면 - ID검색");
		PageInfo<Member> memberPage = memberMentorService.getMentorList(pageable, keyId);
		PageInfo<Member> mentorList = memberMentorService.getMentorList(pageable, keyId);
		model.addAttribute("mentorList", memberPage.getContents());
		model.addAttribute("pageInfo", memberPage);
		model.addAttribute("mentorList", mentorList);
		
		return "member/mentorList/mentorList"; 
	}

}
