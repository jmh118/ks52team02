package ks52team02.member.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/mentee")
public class MenteeMypageController {

	// 김광민
	
	@GetMapping("/account")
    public String MoveMypageAccount() {
        System.out.println("mypage account 페이지 이동");
        return  "member/mypage/mentee/menteeMypageAccount";
        
    }
	
	@GetMapping("/introduce")
    public String MoveMypageIntroduce() {
        System.out.println("mypage introduce 페이지 이동");
        return  "member/mypage/mentee/menteeMypageIntroduce";
    }
	
	@GetMapping("/career")
    public String MoveMypageCareer() {
        System.out.println("mypage career 페이지 이동");
        return  "member/mypage/mentee/menteeMypageCareer";
    }
	
	@GetMapping("/educationAdd")
    public String MoveMypageEducationAdd() {
        System.out.println("mypage Education 추가 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypageEducationAdd";
    }

    @GetMapping("/educationModify")
    public String MoveMypageEducationModify() {
        System.out.println("mypage Education 수정 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypageEducationModify";
    }
	
    @GetMapping("/certificateAdd")
    public String MoveMypageCertificateAdd() {
        System.out.println("mypage Certificate 추가 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypageCertificateAdd";
    }

    @GetMapping("/certificateModify")
    public String MoveMypageCertificateModify() {
        System.out.println("mypage Certificate 수정 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypageCertificateModify";
    }
	
    @GetMapping("/portfolioAdd")
    public String MoveMypagePortfolioAdd() {
        System.out.println("mypage Portfolio 추가 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypagePortfolioAdd";
    }

    @GetMapping("/portfolioModify")
    public String MoveMypagePortfolioModify() {
        System.out.println("mypage Portfolio 수정 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypagePortfolioModify";
    }
    
    
	

    
}
