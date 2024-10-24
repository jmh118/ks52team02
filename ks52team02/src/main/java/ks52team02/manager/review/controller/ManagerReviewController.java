package ks52team02.manager.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks52team02.manager.review.dto.Review;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
import ks52team02.manager.review.service.ManagerReviewService;
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
	public String deleteReviews(@RequestParam(name="reviewCode") String reviewCode, Model model) {
		
		log.info("컨트롤러 실행 ");
		log.info("리뷰 코드 : {}", reviewCode);
		
		managerReviewService.removeReview(reviewCode);
		
		return "redirect:/manager/review/list";
	}
	
	
	
	@GetMapping("/list")
    public String getAllReviews(Model model) {
		
		List<Review> reviewList = managerReviewMapper.getAllMentoringReviewList();
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("title", "멘토링 후기 관리");
		model.addAttribute("content", "멘토링 리뷰 조회 (내용이 부적절할 시 관리자가 삭제)");
    	
    	
        return  "manager/review/reviewList";
	}

}
