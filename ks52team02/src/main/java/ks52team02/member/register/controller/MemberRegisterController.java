package ks52team02.member.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class MemberRegisterController {
	
	@GetMapping("/info")
	public String moveJoinInfo() {
		System.out.println("회원가입 | 회원가입 안내 화면");
		return  "member/register/registerInfo";
	}
	
	@GetMapping("/mentee")
    public String registerMentee() {
    	System.out.println("멘티 회원가입 화면");
        return  "member/register/registerMenteeForm";
    }

	@GetMapping("/mentor1")
    public String registerMentor1() {
    	System.out.println("멘토 회원가입 화면1");
        return  "member/register/registerMentor1Form";
    }

	@GetMapping("/mentor2")
    public String registerMento2r() {
    	System.out.println("멘토 회원가입 화면2");
        return  "member/register/registerMentor2Form";
    }

}
