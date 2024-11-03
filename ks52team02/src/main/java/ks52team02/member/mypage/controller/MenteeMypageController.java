package ks52team02.member.mypage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks52team02.member.mypage.dto.MenteeCertificate;
import ks52team02.member.mypage.dto.MenteeEducation;
import ks52team02.member.mypage.dto.MenteeInfo;
import ks52team02.member.mypage.dto.MenteePortfolio;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.member.mypage.mapper.MenteeMypageMapper;
import ks52team02.member.mypage.service.MenteeMypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/mentee")
@Slf4j
public class MenteeMypageController {
	
	private final MenteeMypageService menteeMypageService;
	private final MenteeMypageMapper menteeMypageMapper;
	
	//계정정보 수정
	@PostMapping("/account")
	public String MoveMypageAccount(MenteeInfo menteeInfo) {
		menteeMypageService.modifyMentee(menteeInfo);
		
		return "redirect:/mypage/mentee/account";
	}
	
	//소개글 수정
	@PostMapping("/introduce")
	public String MoveMypageIntroduce(MenteeProfile menteeProfile) {
		menteeMypageService.modifyIntroduce(menteeProfile);
		return "redirect:/mypage/mentee/account";
	}
	
	@GetMapping("/account")
    public String MoveMypageAccount(HttpServletRequest request, Model model) {
        System.out.println("mypage account 페이지 이동");
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("SID");
        //로그인 된 아이디로 계정 정보 조회
        MenteeInfo menteeInfo = menteeMypageMapper.getMenteeInfoById(sessionId);
        //로그인 된 아이디로 소개글 조회
        MenteeProfile menteeProfile= menteeMypageMapper.getIntroduceById(sessionId);
        //아이디로 학력 조회
        List<MenteeEducation> menteeEducationInfo = menteeMypageMapper.getMenteeEducationById(sessionId);
        //아이디로 자격증 조회
        List<MenteeCertificate> menteeCertificateInfo = menteeMypageMapper.getMenteeCertificateById(sessionId);
        //아이디로 포트폴리오 조회
        List<MenteePortfolio> menteePortfolioInfo = menteeMypageMapper.getMenteePortfolioById(sessionId);
        
        model.addAttribute("menteeInfo", menteeInfo);
        model.addAttribute("menteeProfile", menteeProfile);
        model.addAttribute("menteeEducationInfo", menteeEducationInfo);
        model.addAttribute("menteeCertificateInfo", menteeCertificateInfo);
        model.addAttribute("menteePortfolioInfo", menteePortfolioInfo);
        
        
        return  "member/mypage/mentee/menteeMypage";
        
    }
	
	@PostMapping("/educationAdd")
	public String addEducationInfo(MenteeEducation menteeEducation) {
		menteeMypageService.addEducationInfo(menteeEducation);
		return "redirect:/mypage/mentee/account";
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
