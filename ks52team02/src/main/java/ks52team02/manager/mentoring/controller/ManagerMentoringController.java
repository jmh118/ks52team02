package ks52team02.manager.mentoring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.manager.mentoring.service.ManagerMentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/mentoring")
@Slf4j
public class ManagerMentoringController {
	
	private final ManagerMentoringService managerMentoringService;
	
	@GetMapping("/noticeList")
    public String moveManagerNoticeList(Model model) {
		
		List<ManagerMetoringNotice> noticeList = managerMentoringService.getManagerNoticeList();
    	model.addAttribute("noticeList", noticeList);
		
		System.out.println("멘토링 공고 조회 페이지 이동");
        return  "manager/mentoring/noticeList";
    }


	
}
