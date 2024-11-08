package ks52team02.member.withdrawal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.member.withdrawal.service.MemberWithdrawalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/withdrawal")
public class MemberWithdrawalController {
	
	private final MemberWithdrawalService memberWithdrawalService;
	
	
	@PostMapping("/form")
	public String removeMember(WithdrawalMember withdrawalMember, HttpSession session) {
		System.out.println("탈퇴 신청");
		String withdrawalMemberId = (String) session.getAttribute("SID");
		String withdrawalMemberLevelCode = (String) session.getAttribute("SLEVEL");
		withdrawalMember.setWithdrawalMemberId(withdrawalMemberId);
		withdrawalMember.setWithdrawalMemberLevelCode(withdrawalMemberLevelCode);
		
		int result = memberWithdrawalService.memberWithdrawal(withdrawalMember);
		
		if(result>0) {
			session.invalidate();
		}
		return "redirect:/member";
	}
	
	@GetMapping("/form")
    public String removeMember() {
    	System.out.println("탈퇴 신청 화면");
    	
        return  "member/withdrawal/applicationWithdrawalForm";
    }


}
