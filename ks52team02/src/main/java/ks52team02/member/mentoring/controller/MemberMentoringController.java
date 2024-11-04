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
	
	@PostMapping("/modifyAnswer")
	public String getmodifyAnswer(NoticeAnswer noticeAnswer,@RequestParam(name="noticeCode")String noticeCode) {
		mentoringService.modifyAnswer(noticeAnswer);
		
		return "redirect:/mentoring/noticeDetail?noticeCode=" + noticeCode;
	}
	
	@PostMapping("/modifyQuestion")
	public String getmodifyQuestion(NoticeQuestion noticeQuestion) {
		mentoringService.modifyQuestion(noticeQuestion);
		
		return "redirect:/mentoring/notice";
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
	public boolean applyCheck(Member member) {
		boolean isDuplicate = false;
		isDuplicate = mentoringService.getApplyCheck(member);
		
		return isDuplicate; 
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
		
		
		log.info("notice: {}", notice);
		mentoringService.addNotice(notice);
		log.info("noticeDetail {}",noticeDetail);
		mentoringService.addNoticeDetail(noticeDetail);
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

    	
    	List<NoticeDetail> noticeDetailYmd = mentoringMapper.getNoticeApplyYmdByCode(noticeCode);
    	model.addAttribute("noticeDetailYmd", noticeDetailYmd);

        return  "member/mentoring/noticeDetail";
    }
	
	@GetMapping("/notice")
	public String movenoticeList(@RequestParam(required = false) String category, Model model,Pageable pageable) {
	
		List<Topic> categoryCount = mentoringService.getCategoryCountList();
		PageInfo<Notice> noticeList = mentoringService.getNoticeList(category, pageable);
		
		model.addAttribute("noticeList", noticeList);
		log.info("noticeList :{}",noticeList);
		model.addAttribute("categoryCount", categoryCount);
		
    	System.out.println("멘토링 | 멘토링 공고 조회 화면");
        return  "member/mentoring/noticeList";
    }


}
