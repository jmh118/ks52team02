package ks52team02.member.mentoring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mentoring.mapper.MentoringMapper;
import ks52team02.member.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentoring")
@Slf4j
public class MemberMentoringController {

	private final MentoringService mentoringService;
	private final MentoringMapper mentoringMapper;
	
	@PostMapping("/apply")
	public String addMentoringApply(MentoringApply mentoringApply) {
		
		mentoringService.addMentoringApply(mentoringApply);
		
		return "redirect:/mentoring/notice";
	}
	
	@PostMapping("/noticeAnswer")
	public String addNoticeAnswer(NoticeAnswer noticeAnswer,@RequestParam(name="noticeCode")String noticeCode) {
		
		mentoringService.addNoticeAnswer(noticeAnswer);
		
		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@PostMapping("/noticeQuestion")
	public String addNoticeQuestion(NoticeQuestion noticeQuestion,@RequestParam(name="noticeCode")String noticeCode) {
		
		mentoringService.addNoticeQuestion(noticeQuestion);

		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@PostMapping("/noticeAdd")
	public String addNotice(Notice notice) {
		
		
		log.info("notice: {}", notice);
		mentoringService.addNotice(notice);
		
		return "redirect:/mentoring/notice";
	}
	
	@GetMapping("/noticeAdd")
	public String movenoticeAdd(Model model) {
    	System.out.println("멘토링 | 멘토링 공고 등록");
    	
    	List<Topic> topicList = mentoringService.getTopicList();
    	model.addAttribute("topicList", topicList);
    	
        return  "member/mentoring/noticeAdd";
    }
	
	@GetMapping("/noticeDetail")
    public String MoveNoticeDetail(@RequestParam(name="noticeCode")String noticeCode, HttpSession session, Model model) {
    	System.out.println("멘토링 | 멘토링 공고 조회 | 멘토링 공고 상세 조회 화면");
    	// 공고상세내용
    	Notice noticeDetail = mentoringMapper.getNoticeDetailByCode(noticeCode);
    	List<NoticeDetail> mentoringTime = mentoringService.getNoticeDetailTimeByCode(noticeCode);
    	model.addAttribute("noticeDetail", noticeDetail);
    	model.addAttribute("mentoringTime", mentoringTime);
    	
    	// 공고관련질문
    	List<NoticeQuestion> noticeQnA = mentoringService.getNoticeQuestionByCode(noticeCode);
    	log.info("noticeQnA : {}",noticeQnA);
    	model.addAttribute("noticeQnA", noticeQnA);
    	System.out.println(noticeQnA);
    	
    	List<NoticeDetail> noticeDetailYmd = mentoringMapper.getNoticeApplyYmdByCode(noticeCode);
    	model.addAttribute("noticeDetailYmd", noticeDetailYmd);
    	String memberGrade = (String)session.getAttribute("SLEVEL");
    	System.out.println("멤버등급"+memberGrade);
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
    public String moveMentoringApply(@RequestParam(name="noticeCode")String noticeCode, Model model) {
		
    	System.out.println("멘토링 신청 화면");
    	List<NoticeDetail> noticeDetailYmd = mentoringMapper.getNoticeApplyYmdByCode(noticeCode);
    	model.addAttribute("noticeDetailYmd", noticeDetailYmd);
    	
    	log.info("noticeDetailYmd : {}",noticeDetailYmd);

        return  "member/mentoring/mentoringApply";
    }
	
	

}
