package ks52team02.member.mentoring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mentoring.mapper.MentoringMapper;
import ks52team02.member.mentoring.service.MentoringService;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mentoring")
@Slf4j
public class MemberMentoringController {

	private final MentoringService mentoringService;
	private final MentoringMapper mentoringMapper;
	
	@GetMapping("/removeNoticeQuestion")
	public String removeNoticeQuestion(@RequestParam(name="questionCode") String questionCode, @RequestParam(name="noticeCode") String noticeCode) {
		
		mentoringService.removeNoticeQuestion(questionCode);
		
		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@GetMapping("/removeNoticeAnswer")
	public String removeNoticeAnswer(@RequestParam(name="answerCode") String answerCode, @RequestParam(name="noticeCode") String noticeCode) {
		
		mentoringService.removeNoticeAnswer(answerCode);
		
		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@PostMapping("/modifyAnswer")
	public String modifyAnswer(NoticeAnswer noticeAnswer, @RequestParam(name="noticeCode")String noticeCode) {
		
		mentoringService.modifyAnswer(noticeAnswer);
		
		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@PostMapping("/modifyQuestion")
	public String modifyQuestion(NoticeQuestion noticeQuestion, @RequestParam(name="noticeCode")String noticeCode) {
		
		mentoringService.modifyQuestion(noticeQuestion);
		
		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@GetMapping("/applyMenteeProfile")
	public String getapplyMenteeProfile(HttpSession session, Model model) {
		
		String memberID = (String) session.getAttribute("SID");
		List<MenteeProfile> menteeProfile = mentoringService.getApplyMenteeProfileById(memberID);
		
		model.addAttribute("activeMenu", "info");
		model.addAttribute("menteeProfile", menteeProfile);
		
		return "member/mentoring/applyMenteeProfile";
	}
	
	@PostMapping("/applyCheck")
	@ResponseBody
	public Member applyCheck(@RequestParam(value="searchId") String searchId) {

		Member memberInfo = mentoringService.checkApply(searchId);
		
		return memberInfo; 
	}
	
	@PostMapping("/modifyNotice")
	public String modifyNotice(Notice notice) {
		
		mentoringService.modifyNotice(notice);
		
		return "redirect:/mentoring/notice";
	}
	
	@GetMapping("/modifyNotice")
	public String modifyNotice(@RequestParam(name="noticeCode") String noticeCode, Model model) {
		
		List<Topic> topicList = mentoringService.getTopicList();
    	model.addAttribute("topicList", topicList);
    	
    	Notice noticeInfo = mentoringService.getNoticeInfoByCode(noticeCode);
    	model.addAttribute("noticeInfo", noticeInfo);
		
		return "member/mentoring/noticeModify";
	}
	
	@PostMapping("/apply")
	public String addMentoringApply(MentoringApply mentoringApply) {
		
		mentoringService.addMentoringApply(mentoringApply);
		
		return "redirect:/pay/beforeList";
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
	public String addNotice(Notice notice, NoticeDetail noticeDetail) {
		
		mentoringService.addNotice(notice);
		mentoringService.addNoticeDetail(noticeDetail);
		return "redirect:/mentoring/notice";
	}
	
	@GetMapping("/noticeAdd")
	public String addMentoringNotice(Model model) {
    	
    	List<Topic> topicList = mentoringService.getTopicList();
    	model.addAttribute("topicList", topicList);
    	
        return  "member/mentoring/noticeAdd";
    }
	
	@GetMapping("/noticeDetail")
    public String getNoticeDetail(@RequestParam(name="noticeCode")String noticeCode, HttpSession session, Model model) {

    	// 공고상세내용
    	Notice noticeDetail = mentoringMapper.getNoticeDetailByCode(noticeCode);
    	List<NoticeDetail> mentoringTime = mentoringService.getNoticeDetailTimeByCode(noticeCode);
    	model.addAttribute("noticeDetail", noticeDetail);
    	model.addAttribute("mentoringTime", mentoringTime);
    	
    	// 공고관련질문
    	List<NoticeQuestion> noticeQnA = mentoringService.getNoticeQuestionByCode(noticeCode);
    	log.info("noticeQnA : {}",noticeQnA);
    	model.addAttribute("noticeQnA", noticeQnA);

    	List<NoticeDetail> noticeDetailYmd = mentoringMapper.getNoticeApplyYmdByCode(noticeCode);
    	model.addAttribute("noticeDetailYmd", noticeDetailYmd);

        return  "member/mentoring/noticeDetail";
    }
	
	@GetMapping("/notice")
	public String getNoticeList(@RequestParam(name="category",required = false) String category, Model model,Pageable pageable) {
	
		List<Topic> categoryCount = mentoringService.getCategoryCountList();
		PageInfo<Notice> noticeList = mentoringService.getNoticeList(category, pageable);
		
		log.info("noticeList :{}",noticeList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("categoryCount", categoryCount);
		
		if (category != null && !category.isEmpty()) {				
			model.addAttribute("category", category);
		}
		
        return  "member/mentoring/noticeList";
    }


}
