package ks52team02.manager.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/review")
public class ManagerReviewController {
	
	@GetMapping("/list")
    public String getAllReviews() {
    	System.out.println("멘토링 리뷰 조회 화면");
        return  "manager/review/reviewList";
	}

}
