package ks52team02.member.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/mentor")
public class MentorMypageController {

	@GetMapping("/account")
    public String MoveMypageAccount() {
        System.out.println("mypage account 페이지 이동");
        return  "member/mypage/mentor/mentorMypageAccount";
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
