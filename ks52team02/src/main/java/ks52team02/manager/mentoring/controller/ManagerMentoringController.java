package ks52team02.manager.mentoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/mentoring")
public class ManagerMentoringController {
	
	@GetMapping("/noticeList")
    public String dormantMembers() {
    	System.out.println("멘토링 공고 조회 페이지 이동");
        return  "manager/mentoring/noticeList";
    }

	
	
	
	
	
}
