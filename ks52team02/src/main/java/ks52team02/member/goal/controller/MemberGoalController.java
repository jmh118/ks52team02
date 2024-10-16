package ks52team02.member.goal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goal")
public class MemberGoalController {

	@GetMapping("/list")
	public String getMenteeGoalList() {
		System.out.println("멘토 목표 조회");
		return  "member/goal/goalList";
	}
	
	@GetMapping("/recordList")
	public String getMenteeGoalRecordList() {
		System.out.println("멘토 목표 진행 조회");
		return  "member/goal/goalRecordList";
	}
	
	@GetMapping("/recordDetail")
	public String getMenteeGoalRecordDetail() {
		System.out.println("멘토 목표 진행 상세 조회");
		return  "";
	}
	
	@GetMapping("/add")
	public String addMenteeGoal() {
		System.out.println("멘티 목표 설정 추가");
		return  "";
	}
	
	@GetMapping("/modify")
	public String modifyMenteeGoal() {
		System.out.println("멘티 목표 설정 수정");
		return  "";
	}
	
	@GetMapping("/recordAdd")
	public String addMenteeGoalRecord() {
		System.out.println("멘티 목표 진행 추가");
		return  "";
	}
	
	@GetMapping("/recordModify")
	public String modifyMenteeGoalRecord() {
		System.out.println("멘티 목표 진행 수정");
		return  "";
	}
}
