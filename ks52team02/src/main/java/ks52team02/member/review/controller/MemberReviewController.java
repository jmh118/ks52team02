package ks52team02.member.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.service.MemberPayService;
import ks52team02.member.review.mapper.MemberReviewMapper;
import ks52team02.member.review.service.MemberReviewService;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/review")
public class MemberReviewController {
	
	private final MemberPayService memberPayService;
	private final MemberReviewService memberReviewService;
	private final MemberReviewMapper memberReviewMapper;
	
	
	@GetMapping("/mentorReviewList")
	public String mentorReviewList(@RequestParam(name="memberId") String memberId, Model model) {
		
		List<Review> reviewList = memberReviewService.getReviewListByMentor(memberId);
		String memberEmail = memberReviewService.getMentorEmailById(memberId);
		double reviewAvg = memberReviewService.getMentorReviewAvg(memberId);
		
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberEmail", memberEmail);
		model.addAttribute("reviewAvg", reviewAvg);
		model.addAttribute("reviewList", reviewList);
		
		return "member/review/mentorReviewList";
	}
	
	
	@PostMapping("/add")
	public String addReview(Review review, HttpSession session) {
		
		String menteeId = (String) session.getAttribute("SID");		
		review.setMenteeId(menteeId);
		
		memberReviewService.addReview(review);
		
		return "redirect:/review/list";
	}
	
	
	@GetMapping("/form")
	public String addMentoringReview(@RequestParam(name="payCode") String payCode, Model model) {

		String noticeTitle = memberPayService.getMentoringTitleByPayCode(payCode);
		model.addAttribute("noticeTitle", noticeTitle);
		model.addAttribute("payCode", payCode);
		
		return "member/review/reviewForm";
	}
	
	
	@PostMapping("/modify")
	public String modifyReview(Review review) {
		
		memberReviewMapper.modifyReview(review);
		
		return "redirect:/review/list";
	}
	
	@GetMapping("/modify")
	public String modifyMentoringReview(@RequestParam(name="reviewCode") String reviewCode, HttpSession session, Model model) {
		
		String memberId = (String) session.getAttribute("SID");
		Review review = memberReviewService.getReviewByReviewCode(reviewCode, memberId);

		model.addAttribute("review", review);
		
		return "member/review/reviewModifyForm";
	}

	@GetMapping("/list")
	public String getReviewListByMentee(Model model, HttpSession session, Pageable pageable) {
		
		String memberId = (String) session.getAttribute("SID");
		PageInfo<Review> reviewList = memberReviewService.getReviewListById(memberId, pageable);
		
		model.addAttribute("activeMenu", "reviewList");
		model.addAttribute("reviewList", reviewList);
		
		return "member/review/reviewListByMentee";
	}
}
