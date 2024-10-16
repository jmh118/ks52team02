package ks52team02.manager.goal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.goal.dto.MemberGoal;
import ks52team02.manager.goal.mapper.GoalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/goal")
public class ManagerGoalController {
	
	private final GoalMapper goalMapper;
	
	@GetMapping("/setList")
    public String moveMentoringGoalSetList(Model model) {		
		
		List<MemberGoal> goalList = goalMapper.getMemberGoalList();
		log.info("goalList : {}", goalList);
		
		model.addAttribute("goalList", goalList);

		log.info("멘티 목표 설정 조회 화면");
        return  "manager/goal/goalList";
    }

	@GetMapping("/recordList")
    public String moveMentoringGoalSettingList() {
		log.info("멘티 목표 진행 조회 화면");
        return  "manager/goal/goalRecordList";
    }

}
