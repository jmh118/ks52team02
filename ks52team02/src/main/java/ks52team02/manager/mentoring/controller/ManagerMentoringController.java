package ks52team02.manager.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
			if (searchId != null && !searchId.isEmpty()) {				
				model.addAttribute("searchId", searchId);
			}
		return "manager/mentoring/mentoringApplyList";
	}
	
	@GetMapping("/removeQuestion")
	@ResponseBody
	public boolean removeQuestions(@RequestParam(name="questionCode") String questionCode ) {
		boolean isDel = false;
	
		int result = managerMentoringService.removeQuestion(questionCode);
		if(result > 0) isDel = true;
		return isDel;
	}
	
	@GetMapping("/removeAnswer")
	@ResponseBody
	public boolean removeAnswers(@RequestParam(name="answerCode") String answerCode ) {
		boolean isDel = false;
		
		int result = managerMentoringService.removeAnswer(answerCode);
		if (result > 0) isDel = true;
		return isDel;
	}
	
	@GetMapping("/noticeList")
    public String getManagerNoticeList(@RequestParam(name="searchId",required = false) String searchId, Pageable pageable,Model model) {
		
		PageInfo<ManagerMetoringNotice> noticeList = managerMentoringService.getManagerNoticeList(pageable,searchId);
		
    	model.addAttribute("noticeList", noticeList);
    	if (searchId != null && !searchId.isEmpty()) {				
			model.addAttribute("searchId", searchId);
		}
		
		System.out.println("멘토링 공고 조회 페이지 이동");
        return  "manager/mentoring/noticeList";
    }

	@GetMapping("/questionList")
	public String getManagerNoticeQuestionList(@RequestParam(name="searchId",required = false) String searchId, Pageable pageable, Model model) {
		
		PageInfo<NoticeQuestion> questionList = managerMentoringService.getManagerNoticeQuestionList(pageable, searchId);
		
		model.addAttribute("questionList", questionList);
		
		if (searchId != null && !searchId.isEmpty()) {				
			model.addAttribute("searchId", searchId);
		}
		
		return "manager/mentoring/noticeQuestionList";
	}
	
	@GetMapping("/answerList")
	public String getManagerNoticeAnswerList(@RequestParam(name="searchId",required = false) String searchId, Pageable pageable, Model model) {
		
		PageInfo<NoticeAnswer> answerList = managerMentoringService.getManagerNoticeAnswerList(pageable, searchId);
		
		model.addAttribute("answerList", answerList);
		
		if (searchId != null && !searchId.isEmpty()) {				
			model.addAttribute("searchId", searchId);
		}
		
		return "manager/mentoring/noticeAnswerList";
	}
	
}
