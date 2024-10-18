package ks52team02.member.mentoring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentoring")
@Slf4j
public class MemberMentoringController {

	private final MentoringService mentoringService;
	
	@PostMapping("/noticeAdd")
	public String addNotice(Notice notice) {
		
		String nextCode = mentoringService.getNextNoticeCode();
		notice.setNoticeCode("mentoring_notice_code_" + nextCode);
		log.info("notice: {}", notice);
		mentoringService.addNotice(notice);
		
		return "redirect:/mentoring/notice";
	}
	
	@GetMapping("/noticeDetail/{code}")
    public String MoveNoticeDetail(@PathVariable("code") String noticeCode, Model model) {
    	System.out.println("멘토링 | 멘토링 공고 조회 | 멘토링 공고 상세 조회 화면");
    	Notice noticeDetail = mentoringService.getNoticeDetailByCode(noticeCode);
    	model.addAttribute("noticeDetail", noticeDetail);
        return  "member/mentoring/noticeDetail";
    }
	
	@GetMapping("/notice")
	public String movenoticeList(@RequestParam(required = false) String category, Model model) {
		
		List<Notice> noticeList;
		System.out.println(category);
		if(category != null && !category.isEmpty()) {
			noticeList = mentoringService.getNoticeByCategory(category);
		}else {
			noticeList = mentoringService.getNoticeList();
		}
		
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
	public String movenoticeAdd(Model model) {
    	System.out.println("멘토링 | 멘토링 공고 등록");
    	
    	List<Topic> topicList = mentoringService.getTopicList();
    	model.addAttribute("topicList", topicList);
    	
        return  "member/mentoring/noticeAdd";
    }

}
