package ks52team02.member.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class MemberReviewController {
	
	@GetMapping("/list")
	public String getReviewListByMentee() {
		System.out.println("내가 쓴 후기 조회 화면");
		return "member/review/reviewListByMentee";
	}
	
	@GetMapping("/form")
	public String addMentoringReview() {
		System.out.println("멘토링 후기 등록 화면");
		return "member/review/reviewForm";
	}
	
	@GetMapping("/modify")
	public String modifyMentoringReview() {
		System.out.println("내가 쓴 후기 조회 | 멘토링 후기 수정 화면");
		return "member/review/reviewModifyForm";
	}
	
	@GetMapping("/mentorReviewList")
	public String mentorReviewList() {
		System.out.println("멘토 아이디별 후기내역 조회");
		return "member/mentorReviewList";
	}

}
