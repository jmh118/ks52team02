package ks52team02.member.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portfolio")
public class MemberPortfolioController {
	
	@GetMapping("/list")
    public String MovePortfolio() {
    	System.out.println("포트폴리오 | 멘티의 포트폴리오 전체 조회 화면");
        return  "member/portfolio/allMenteePortfolioList";
    }
	
	@GetMapping("/detail")
    public String MovePortfolioDetail() {
    	System.out.println("포트폴리오 상세 조회 화면");
        return  "member/portfolio/menteePortfolioDetail";
    }

}
