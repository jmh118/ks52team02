package ks52team02.manager.honor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks52team02.manager.honor.service.ManagerHonorService;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.review.dto.MentorReviewData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager/honor")
public class ManagerHonorController {
	
	private final ManagerHonorService managerHonorService; 
	
	@GetMapping("/approve")
	@ResponseBody
	public String hornoMentorApprove(@RequestParam(name="memberId") String memberId) {
		
		managerHonorService.honorMentorApprove(memberId);
		
		return "redirect:/manager/honor/criteriaList";
	}
	
	@GetMapping("/cancel")
	@ResponseBody
	public String hornoMentorCancel(@RequestParam(name="memberId") String memberId) {
		
		managerHonorService.honorMentorCancel(memberId);
		
		return "redirect:/manager/honor/criteriaList";
	}
	
	@GetMapping("/criteriaList")
	public String getMentorReviewsDataList(Model model) {
		
		List<MentorReviewData> mentorReviewsDataList = managerHonorService.getMentorReviewsDataList();
		
		model.addAttribute("title", "명예멘토 기준 조회");
		model.addAttribute("mentorReviewsDataList", mentorReviewsDataList);
		
		return  "manager/honor/honoraryMentorCriteriaList";
	}
	
	@GetMapping("/list")
	public String getHonorMentorList(Model model) {
		
		List<Member> honorMentorList = managerHonorService.getHornorMentorList();
		
		model.addAttribute("title", "명에 멘토 조회");
		model.addAttribute("honorMentorList", honorMentorList);
		
		return  "manager/honor/honoraryMentorList";
	}

}
