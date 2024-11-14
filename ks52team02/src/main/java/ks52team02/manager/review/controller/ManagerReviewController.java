package ks52team02.manager.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks52team02.manager.review.dto.Review;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
import ks52team02.manager.review.service.ManagerReviewService;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager/review")
public class ManagerReviewController {
	
	private final ManagerReviewMapper managerReviewMapper;
	private final ManagerReviewService managerReviewService;
	
	
	@GetMapping("remove")
	@ResponseBody
	public boolean deleteReviews(@RequestParam(name="reviewCode") String reviewCode, Model model) {
		
		boolean isDel = false;
		
		int result = managerReviewService.removeReview(reviewCode);
		
		if(result > 0) isDel = true;
		
		return isDel;
	}
	
	
	
	@GetMapping("/list")
    public String getAllReviews(Pageable pageable, Model model) {
		
		PageInfo<Review> reviewList = managerReviewService.getAllMentoringReviewList(pageable);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("title", "멘토링 후기 관리");
		model.addAttribute("content", "멘토링 리뷰 조회 (내용이 부적절할 시 관리자가 삭제)");
    	
    	
        return  "manager/review/reviewList";
	}

}
