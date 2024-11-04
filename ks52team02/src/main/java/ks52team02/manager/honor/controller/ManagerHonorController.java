package ks52team02.manager.honor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks52team02.manager.honor.service.ManagerHonorService;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.review.dto.MentorReviewData;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager/honor")
public class ManagerHonorController {
	
	private final ManagerHonorService managerHonorService; 
	
	@GetMapping("/approve")
	public String hornoMentorApprove(@RequestParam(name="memberId") String memberId) {
		
		managerHonorService.honorMentorApprove(memberId);
		
		return "redirect:/manager/honor/criteriaList";
	}
	
	@GetMapping("/cancel")
	public String hornoMentorCancel(@RequestParam(name="memberId") String memberId) {
		
		managerHonorService.honorMentorCancel(memberId);
		
		return "redirect:/manager/honor/criteriaList";
	}
	
	@GetMapping("/criteriaList")
	public String getMentorReviewsDataList(Pageable pageable, Model model) {
		
		PageInfo<MentorReviewData> mentorReviewsDataList = managerHonorService.getMentorReviewsDataList(pageable);
		
		model.addAttribute("title", "명예멘토 기준 조회");
		model.addAttribute("mentorReviewsDataList", mentorReviewsDataList);
		
		return  "manager/honor/honoraryMentorCriteriaList";
	}
	
	@GetMapping("/list")
	public String getHonorMentorList(Pageable pageable, Model model) {
		
		PageInfo<Member> honorMentorList = managerHonorService.getHornorMentorList(pageable);
		
		model.addAttribute("title", "명에 멘토 조회");
		model.addAttribute("honorMentorList", honorMentorList);
		
		return  "manager/honor/honoraryMentorList";
	}

}
