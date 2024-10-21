package ks52team02.manager.honor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.honor.mapper.ManagerHonorMapper;
import ks52team02.manager.member.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/manager/honor")
public class ManagerHonorController {
	
	private final ManagerHonorMapper managerHonorMapper;
	
	@GetMapping("/criteriaList")
	public String managerHonoraryMentorCriteriaList() {
		System.out.println("관리자 명예멘토 기준 조회 내역 화면");
		return  "manager/honor/honoraryMentorCriteriaList";
	}
	
	@GetMapping("/list")
	public String managerHonoraryMentorList(Model model) {
		System.out.println("관리자 명예멘토 조회 화면");
		
		List<String> honorMentorIdList = managerHonorMapper.getHonorMentorIdList();
		List<Member> honorMentorList = managerHonorMapper.getHornorMentorList(honorMentorIdList);
		
		log.info("잘 받?? : {}", honorMentorList);
		
		model.addAttribute("title", "명에 멘토 조회");
		model.addAttribute("honorMentorList", honorMentorList);
		
		return  "manager/honor/honoraryMentorList";
	}

}
