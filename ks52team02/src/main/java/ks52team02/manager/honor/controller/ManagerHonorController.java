package ks52team02.manager.honor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.honor.mapper.ManagerHonorMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.review.dto.MentorReviews;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager/honor")
public class ManagerHonorController {
	
	private final ManagerHonorMapper managerHonorMapper;
	private final ManagerReviewMapper managerReviewMapper;
	
	@GetMapping("/criteriaList")
	public String managerHonoraryMentorCriteriaList(Model model) {
		
		List<MentorReviews> mentorReviewsDataList = managerReviewMapper.getMentorReviewsDataList();
		
		model.addAttribute("title", "명예멘토 기준 조회");
		model.addAttribute("content", "멘티의 명예멘토 기준 (평점 평균과 평점 개수, 플랫폼 내 기준) 충족 시 승인 버튼 활성화 후 명예멘토로 승인");		
		model.addAttribute("mentorReviewsDataList", mentorReviewsDataList);
		
		return  "manager/honor/honoraryMentorCriteriaList";
	}
	
	@GetMapping("/list")
	public String managerHonoraryMentorList(Model model) {
		
		List<String> honorMentorIdList = managerHonorMapper.getHonorMentorIdList();
		List<Member> honorMentorList = managerHonorMapper.getHornorMentorList(honorMentorIdList);
		
		model.addAttribute("title", "명에 멘토 조회");
		model.addAttribute("honorMentorList", honorMentorList);
		
		return  "manager/honor/honoraryMentorList";
	}

}
