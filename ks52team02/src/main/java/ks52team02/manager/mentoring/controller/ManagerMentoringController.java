package ks52team02.manager.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.manager.mentoring.service.ManagerMentoringService;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/mentoring")
@Slf4j
public class ManagerMentoringController {
	
	private final ManagerMentoringService managerMentoringService;
	
	@GetMapping("/applyList")
	public String getManagerMentoringApplyList(@RequestParam(name="searchId",required = false) String searchId, Pageable pageable, Model model) {
		
			PageInfo<MentoringApply> applyList = managerMentoringService.getManagerMentoringApplyList(pageable, searchId);
			
			model.addAttribute("applyList", applyList);
			model.addAttribute("searchId", searchId);
		return "manager/mentoring/mentoringApplyList";
	}
	
	@GetMapping("/removeQuestion")
	public String removeQuestions(@RequestParam(name="questionCode") String questionCode, Model model) {
		managerMentoringService.removeQuestion(questionCode);
		return "redirect:/manager/mentoring/questionList";
	}
	
	@GetMapping("/removeAnswer")
	public String removeAnswers(@RequestParam(name="answerCode") String answerCode, Model model) {
		managerMentoringService.removeAnswer(answerCode);
		
		return "redirect:/manager/mentoring/answerList";
	}
	
	@GetMapping("/noticeList")
    public String moveManagerNoticeList(@RequestParam(name="searchId",required = false) String searchId, Pageable pageable,Model model) {
		
		PageInfo<ManagerMetoringNotice> noticeList = managerMentoringService.getManagerNoticeList(pageable,searchId);
		
    	model.addAttribute("noticeList", noticeList);
		
		System.out.println("멘토링 공고 조회 페이지 이동");
        return  "manager/mentoring/noticeList";
    }

	@GetMapping("/questionList")
	public String moveManagerNoticeQuestionList(Pageable pageable, Model model) {
		
		PageInfo<NoticeQuestion> questionList = managerMentoringService.getManagerNoticeQuestionList(pageable);
		
		model.addAttribute("questionList", questionList);
		
		return "manager/mentoring/noticeQuestionList";
	}
	
	@GetMapping("/answerList")
	public String moveManagerNoticeAnswerList(Pageable pageable, Model model) {
		
		PageInfo<NoticeAnswer> answerList = managerMentoringService.getManagerNoticeAnswerList(pageable);
		
		model.addAttribute("answerList", answerList);
		return "manager/mentoring/noticeAnswerList";
	}
	
}
