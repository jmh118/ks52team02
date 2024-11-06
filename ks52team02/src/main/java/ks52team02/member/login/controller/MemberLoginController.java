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

		log.info("아이디 확인 : {}", session.getAttribute("SID"));
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
	public String loginProcess(String memberId, String memberPw,
							   HttpSession session, RedirectAttributes reAttr) {
		
		
		String viewName = "redirect:/member/login";
		String msg = "회원의 정보가 일치하지 않습니다. 다시 로그인해주세요~";
		
		Map<String, Object> loginMap = memberLoginService.checkedMember(memberId, memberPw);
		boolean checkMember = (boolean) loginMap.get("isCheck");
		
		if(checkMember) {
			
			Member memberInfo = (Member) loginMap.get("memberInfo");
			String memberLevel = memberInfo.getMemberLevel();
			
			if(memberLevel.equals("member_level_manager")) {
				viewName = "redirect:/manager";
			} else {
				viewName = "redirect:/member";
			} 
			
			session.setAttribute("SID", memberId);
			session.setAttribute("SLEVEL", memberLevel);
					
		}else {
			
			reAttr.addAttribute("msg", msg);
		}
	
		
		return viewName;
	}
	
	
	@GetMapping("/managerLogin")
	public String managerLoginView() {
		System.out.println("관리자 로그인 화면");
		return  "member/login/managerLogin";
	}
	
	@GetMapping("/login")
	public String loginView() {
		System.out.println("로그인 화면");
		return  "member/login/login";
	}


}
