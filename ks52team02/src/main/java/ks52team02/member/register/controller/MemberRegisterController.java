package ks52team02.member.register.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.register.mapper.MemberRegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/register")
public class MemberRegisterController {
	
	private final MemberRegisterMapper memberRegisterMapper;
	
	
	@GetMapping("/info")
	public String moveJoinInfo() {
		System.out.println("회원가입 | 회원가입 안내 화면");
		return  "member/register/registerInfo";
	}
	
	@GetMapping("/mentee")
    public String registerMentee(Model model) {
		model.addAttribute("title", "회원가입");
    	System.out.println("멘티 회원가입 화면");
    	
        return  "member/register/registerMenteeForm";
    }
	
	@PostMapping("/mentee")
	public String registerMentee(Member member) {
		memberRegisterMapper.register(member);
		System.out.println("멘티 회원가입");
		
		return  "redirect:/manager/memberInfo/membersInfoList";
	}

	@GetMapping("/mentor1")
    public String registerMentor1() {
    	System.out.println("멘토 회원가입 화면1");
        return  "member/register/registerMentor1Form";
    }

	@GetMapping("/mentor2")
    public String registerMento2() {
    	System.out.println("멘토 회원가입 화면2");
        return  "member/register/registerMentor2Form";
    }
	
	
	@PostMapping("/dupicatedCheckById")
	@ResponseBody
	public boolean duplicatedIdCheck(@RequestParam String memberId) {
		log.info("memberId : {}", memberId);
		boolean isDuplicate = false;
		isDuplicate = memberRegisterMapper.dupicatedCheckById(memberId);
		
		return isDuplicate;
	}

}
