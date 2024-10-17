package ks52team02.manager.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.manager.member.service.ManagerMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/manager/member")
public class ManagerMemberController {

	private final ManagerMemberService memberMapperService;
	
	@GetMapping("/list")
    public String allMembers(Model model) {
    	System.out.println("전체 회원 조회 페이지 이동");
    	List<Member> memberList = memberMapperService.getMemberList();
    	model.addAttribute("memberList", memberList);
    	
    	return  "manager/memberInfo/membersInfoList";
	}
	
	@GetMapping("/withdrawalList")
    public String withdrawalMembers(Model model) {
    	System.out.println("탈퇴 회원 조회 페이지 이동");
    	List<WithdrawalMember> withdrawalmemberList = memberMapperService.getWithdrawalMemberList();
    	model.addAttribute("withdrawalmemberList", withdrawalmemberList);
    	return  "manager/memberInfo/withdrawalMembersList";
    }
	
	@GetMapping("/dormantList")
    public String dormantMembers(Model model) {
    	System.out.println("휴면 회원 조회 페이지 이동");
        return  "manager/memberInfo/dormantMembersList";
    }
	
	@GetMapping("/loginLog")
    public String loginLog() {
    	System.out.println("멤버 로그인 로그 조회 페이지 이동");
        return  "manager/memberInfo/memberLoginLogList";
    }
	
	@GetMapping("/waitingForApproval")
    public String waitingForApproval() {
    	System.out.println("멘토 회원가입 승인 대기 내역 페이지 이동");
        return  "manager/memberInfo/waitingForApprovalList";
    }
	
	@GetMapping("/registeredMembers")
    public String managerNewmemberSearch() {
    	System.out.println("신규회원 조회 페이지 이동");
        return  "manager/memberInfo/registeredMembers";
    }
	
	@GetMapping("/waitingForWithdrawal")
    public String waitingForWithdrawal() {
    	System.out.println("회원탈퇴 신청 대기 내역 페이지 이동");
        return  "manager/memberInfo/waitingForWithdrawalList";
    }
	
	@GetMapping("/infoModify")
    public String membersInfoModify() {
    	System.out.println("전체 회원 정보 수정 페이지 이동");
        return  "manager/memberInfo/membersInfoModifyForm";
    }
	
	@GetMapping("/managerWithdrawal")
    public String managerWithdrawal() {
    	System.out.println("관리자 탈퇴 페이지 이동");
        return  "manager/setting/managerWithdrawal";

    }

}
