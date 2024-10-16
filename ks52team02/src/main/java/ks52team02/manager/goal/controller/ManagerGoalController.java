package ks52team02.manager.goal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/goal")
public class ManagerGoalController {
	
	@GetMapping("/setList")
    public String moveMentoringGoalSetList() {
    	System.out.println("멘토링 목표설정 페이지 이동");
        return  "manager/goal/goalList";
    }

	@GetMapping("/recordList")
    public String moveMentoringGoalSettingList() {
    	System.out.println("멘토링 목표진행(블로그) 페이지 이동");
        return  "manager/goal/goalRecordList";
    }

}
