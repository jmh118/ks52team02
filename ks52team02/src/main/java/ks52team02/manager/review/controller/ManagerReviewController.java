package ks52team02.manager.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.review.dto.Review;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager/review")
public class ManagerReviewController {
	
	private final ManagerReviewMapper managerReviewMapper;
	
	@GetMapping("/list")
    public String getAllReviews(Model model) {
		
		List<Review> reviewList = managerReviewMapper.getAllMentoringReviewList();
    
		log.info("잘 받?? : {}", reviewList);
		
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("title", "멘토링 후기 관리");
		model.addAttribute("content", "멘토링 리뷰 조회 (내용이 부적절할 시 관리자가 삭제)");
    	
    	
        return  "manager/review/reviewList";
	}

}
