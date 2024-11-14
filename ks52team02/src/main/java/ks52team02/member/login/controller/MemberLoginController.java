package ks52team02.member.login.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks52team02.manager.member.dto.Member;
import ks52team02.member.login.mapper.MemberLoginMapper;
import ks52team02.member.login.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberLoginController {
	
	private final MemberLoginService memberLoginService;
	private final MemberLoginMapper memberLoginMapper;
	
	@PostMapping("checkPw")
	@ResponseBody
	public boolean checkPw(HttpSession session, @RequestParam(value="memberPw") String memberPw) {

		String memberId = (String) session.getAttribute("SID");
		boolean isCheckPw = memberLoginService.isCheckMemberPw(memberId, memberPw);
		
		return isCheckPw; 
	}
	
	
	@PostMapping("checkLevel")
	@ResponseBody
	public boolean checkLevel(@RequestParam(value="memberId") String memberId) {
		
		boolean isLevelManager = false;

		String memberLevel = memberLoginMapper.getMemberLevelById(memberId);
		
		if (memberLevel != null && memberLevel.equals("member_level_manager")) isLevelManager = true;

		return isLevelManager;
	}
	
	
	@GetMapping("/findPassword")
	public String findPasswordView() {
		System.out.println("로그인 | 비밀번호 찾기 화면");
		
		return  "member/login/findPassword";
	}
	
	@PostMapping("/findPassword")
	@ResponseBody
	public String findPasswordView(@RequestParam("inputId") String inputId) {
		System.out.println("비밀번호 찾기");
		String foundPw = memberLoginMapper.findMemberPwById(inputId);
		System.out.println(foundPw);


	    return foundPw;
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		String viewName = "redirect:/member/login";
		String memberLevel = (String) session.getAttribute("SLEVEL");
		if (memberLevel != null && memberLevel.equals("member_level_manager")) {
	        viewName = "redirect:/member/managerLogin";
	    } 
		session.invalidate();
		return viewName;
	}
	
	@PostMapping("/loginProc")
	public String loginProcess(String memberId, String memberPw, HttpSession session, RedirectAttributes reAttr) {
	    return memberLoginService.loginProcess(memberId, memberPw, session, reAttr);
	}
	
	
	@GetMapping("/managerLogin")
	public String managerLoginView() {
		return  "member/login/managerLogin";
	}
	
	@GetMapping("/login")
	public String loginView() {
		return  "member/login/login";
	}


}
