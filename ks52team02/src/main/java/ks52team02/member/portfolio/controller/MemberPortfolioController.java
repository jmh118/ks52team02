package ks52team02.member.portfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.member.portfolio.dto.Portfolio;
import ks52team02.member.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/portfolio")
@Slf4j
public class MemberPortfolioController {
	
	private final PortfolioService portfolioService;
	
	@GetMapping("/list")
    public String MovePortfolio(Model model) {
    	System.out.println("포트폴리오 | 멘티의 포트폴리오 전체 조회 화면");
    	List<Portfolio> PortfolioList= portfolioService.getPortfolioList();
    	model.addAttribute("PortfolioList", PortfolioList);
    	
        return  "member/portfolio/allMenteePortfolioList";
    }
	
	@GetMapping("/detail")
    public String MovePortfolioDetail() {
    	System.out.println("포트폴리오 상세 조회 화면");
        return  "member/portfolio/menteePortfolioDetail";
    }
	
	
}
