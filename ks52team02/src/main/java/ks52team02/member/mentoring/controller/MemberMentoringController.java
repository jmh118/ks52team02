package ks52team02.member.mentoring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentoring")
@Slf4j
public class MemberMentoringController {

	private final MentoringService mentoringService;
	
	@GetMapping("/noticeDetail")
    public String MoveNoticeDetail() {
    	System.out.println("멘토링 | 멘토링 공고 조회 | 멘토링 공고 상세 조회 화면");
        return  "member/mentoring/noticeDetail";
    }
	
	@GetMapping("/notice")
	public String movenoticeList(Model model) {
		
		List<Notice> noticeList = mentoringService.getNoticeList();
		model.addAttribute("noticeList", noticeList);
		
    	System.out.println("멘토링 | 멘토링 공고 조회 화면");
        return  "member/mentoring/noticeList";
    }
	
	@GetMapping("/apply")
    public String moveMentoringApply() {
		
    	System.out.println("멘토링 신청 화면");
        return  "member/mentoring/mentoringApply";
    }
	
	@GetMapping("/noticeAdd")
	public String movenoticeAdd() {
    	System.out.println("멘토링 | 멘토링 공고 등록");
        return  "member/mentoring/noticeAdd";
    }
}
