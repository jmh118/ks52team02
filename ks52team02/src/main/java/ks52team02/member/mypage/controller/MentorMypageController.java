package ks52team02.member.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import ks52team02.member.mypage.service.MentorMypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/mentor")
@Slf4j
public class MentorMypageController {

	private final MentorMypageMapper mentorMypageMapper;
	private final MentorMypageService mentorMypageService;
	
	//계정정보 수정
	 @PostMapping("/account")
	 public String MoveMypageAccount(MentorInfo mentorInfo) {
		 
		 mentorMypageService.modifyMentor(mentorInfo);
		 
		 log.info("mentorInfo ;{}",mentorInfo);
		 
		 return "redirect:/mypage/mentor/account";
	 }
	
	//계정정보 조회
	@GetMapping("/account")
    public String MoveMypageAccount(HttpServletRequest request, Model model) {
		
        System.out.println("mypage account 페이지 이동");
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("SID");
		
		MentorInfo mentorInfo = mentorMypageMapper.getMentorInfoById(sessionId);
		log.info("mentorInfo:{}", mentorInfo);
		 
		model.addAttribute("mentorInfo", mentorInfo);
		 
        return  "member/mypage/mentor/mentorMypage";
    }

    @GetMapping("/career")
    public String MoveMypageCareer() {
        System.out.println("mypage career 페이지 이동");
        return  "member/mypage/mentor/mentorMypageCareer";
    }
    
    @GetMapping("/workAdd")
    public String MoveMypageWorkAdd() {
        System.out.println("mypage Work 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageWorkAdd";
    }
    
    @GetMapping("/workModify")
    public String MoveMypageWorkModify() {
        System.out.println("mypage Work 수정 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageWorkModify";
    }
    
    @GetMapping("/projectAdd")
    public String MoveMypageProjectAdd() {
        System.out.println("mypage Project 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageProjectAdd";
    }
    
    @GetMapping("/projectModify")
    public String MoveMypageProjectModify() {
        System.out.println("mypage Project 수정 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageProjectModify";
    }
    
    @GetMapping("/educationAdd")
    public String MoveMypageEducationAdd() {
        System.out.println("mypage Education 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageEducationAdd";
    }
    
    @GetMapping("/educationModify")
    public String MoveMypageEducationModify() {
        System.out.println("mypage Education 수정 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageEducationModify";
    }
    
    @GetMapping("/certificateAdd")
    public String MoveMypageCertificateAdd() {
        System.out.println("mypage Certificate 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageCertificateAdd";
    }
    
    @GetMapping("/certificateModify")
    public String MoveMypageCertificateModify() {
        System.out.println("mypage Certificate 수정 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageCertificateModify";
    }
}
