package ks52team02.home.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ks52team02.member.honor.dto.hornorMentor;
import ks52team02.member.honor.service.MemberHonorService;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final MemberHonorService memberHonorService; 
	private final MentoringService mentoringService;
	
	@GetMapping("/")
	public String indexMove() {
		return "index";
	}
	
	@GetMapping(value = {"/member","/member/"})
	public String MemberPageMove(Model model) {
		
		List<hornorMentor> honorMentorList = memberHonorService.getHonorMentorList();
		
		model.addAttribute("honorMentorList", honorMentorList);
		
		List<Notice> noticeList = mentoringService.getNoticeMainList();
		model.addAttribute("noticeList", noticeList);
		
		return "member/memberMain";
	}
	
	@GetMapping("/manager")
    public String managerPageMove() {
        return  "manager/managerMain";
    }

}
