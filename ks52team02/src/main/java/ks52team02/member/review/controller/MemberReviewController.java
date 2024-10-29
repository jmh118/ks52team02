package ks52team02.member.review.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.pay.service.MemberPayService;
import ks52team02.member.review.dto.MemberReview;
import ks52team02.member.review.service.MemberReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/review")
public class MemberReviewController {
	
	private final MemberReviewService memberReviewService;
	private final MemberPayService memberPayService;
	
	@PostMapping("/add")
	public String addReview() {
		
		
		
		return "redirect:/review/list";
	}
	
	
	@PostMapping("/modify")
	public String modifyReview(@RequestParam(name="mentroingRating") double reviewScore, 
							   @RequestParam(name="content") String reviewConetent, 
							   @RequestParam(name="reviewCode") String reviewCode) {
		
		Map<String,Object> reviewMap = new HashMap<String,Object>();
		reviewMap.put("reviewScore", reviewScore);
		reviewMap.put("reviewConetent", reviewConetent);
		reviewMap.put("reviewCode", reviewCode);
		
		memberReviewService.modifyReview(reviewMap);
		
		return "redirect:/review/list";
	}
	
	@GetMapping("/isCheck")
	@ResponseBody
	public boolean isReviewCheck(@RequestParam(value="payCode") String payCode) {
		
		boolean isReview = memberReviewService.isReviewCntPayCode(payCode);
		
		return isReview;
	}
	
	@GetMapping("/list")
	public String getReviewListByMentee(Model model) {
		
		model.addAttribute("activeMenu", "reviewList");
		
		System.out.println("내가 쓴 후기 조회 화면");
		return "member/review/reviewListByMentee";
	}
	
	@GetMapping("/form")
	public String addMentoringReview(@RequestParam(name="noticeTitle") String noticeTitle, Model model) {
		
		System.out.println("################# 제목" + noticeTitle);
		model.addAttribute("noticeTitle", noticeTitle);
		
		System.out.println("멘토링 후기 등록 화면");
		return "member/review/reviewForm";
	}
	
	@GetMapping("/modify")
	public String modifyMentoringReview(HttpSession session, @RequestParam(name="payCode") String payCode, Model model) {
		
	  	String memberId = (String) session.getAttribute("SID");
	  	
	  	MemberReview review = memberReviewService.getReviewById(payCode, memberId);
	  	String mentoringTitle = memberPayService.getMentoringTitleByPayCode(payCode);
	  	
	  	model.addAttribute("review", review);
	  	model.addAttribute("mentoringTitle", mentoringTitle);
	  	
		return "member/review/reviewModifyForm";
	}
	
	@GetMapping("/mentorReviewList")
	public String mentorReviewList() {
		System.out.println("멘토 아이디별 후기내역 조회");
		return "member/review/mentorReviewList";
	}

}
