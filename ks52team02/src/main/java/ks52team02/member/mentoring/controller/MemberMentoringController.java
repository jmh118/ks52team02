package ks52team02.member.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mentoring")
public class MemberMentoringController {

	@GetMapping("/noticeDetail")
    public String MoveNoticeDetail() {
    	System.out.println("멘토링 | 멘토링 공고 조회 | 멘토링 공고 상세 조회 화면");
        return  "member/mentoring/noticeDetail";
    }
	
	@GetMapping("/notice")
	public String movenoticeList() {
    	System.out.println("멘토링 | 멘토링 공고 조회 화면");
        return  "member/mentoring/noticeList";
    }
	
	@GetMapping("/apply")
    public String MoveMentoringApply() {
		
    	System.out.println("멘토링 신청 화면");
        return  "member/mentoring/mentoringApply";
    }
	
	@GetMapping("/noticeAdd")
	public String movenoticeAdd() {
    	System.out.println("멘토링 | 멘토링 공고 등록");
        return  "member/mentoring/noticeAdd";
    }
}
