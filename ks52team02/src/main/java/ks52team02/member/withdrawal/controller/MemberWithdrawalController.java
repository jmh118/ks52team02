package ks52team02.member.withdrawal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/withdrawal")
public class MemberWithdrawalController {
	
	@GetMapping("/form")
    public String removeMember() {
    	System.out.println("탈퇴 신청 화면");
        return  "member/withdrawal/applicationWithdrawalForm";
    }
	

}
