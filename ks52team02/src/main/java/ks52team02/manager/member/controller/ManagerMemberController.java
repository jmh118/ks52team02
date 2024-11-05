package ks52team02.manager.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.manager.member.service.ManagerMemberService;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/manager/member")
public class ManagerMemberController {

	private final ManagerMemberService memberMapperService;
	
	
// 조회 only ▼ ---------------------------------------------------------------------------
	
	@GetMapping("/list")
    public String allMembers(Pageable pageable, Model model) {
    	System.out.println("전체 회원 조회 페이지 이동");
    	PageInfo<Member> memberList = memberMapperService.getMemberList(pageable);
    	model.addAttribute("memberList", memberList);
    	
    	return  "manager/memberInfo/membersInfoList";
	}
	
	@GetMapping("/withdrawalList")
    public String withdrawalMembers(Pageable pageable, Model model) {
    	System.out.println("탈퇴 회원 조회 페이지 이동");
    	PageInfo<WithdrawalMember> withdrawalmemberList = memberMapperService.getWithdrawalMemberList(pageable);
    	model.addAttribute("withdrawalmemberList", withdrawalmemberList);
    	return  "manager/memberInfo/withdrawalMembersList";
    }
	
	@GetMapping("/dormantList")
    public String dormantMembers(Pageable pageable, Model model) {
    	System.out.println("휴면 회원 조회 페이지 이동");
    	PageInfo<Member> dormantMemberList = memberMapperService.getDormantMemberList(pageable);
    	model.addAttribute("dormantMemberList", dormantMemberList);
        return  "manager/memberInfo/dormantMembersList";
    }
	
	@GetMapping("/loginLog")
    public String loginLog(Pageable pageable,Model model) {
    	System.out.println("멤버 로그인 로그 조회 페이지 이동");
    	PageInfo<LoginLog> loginLogList = memberMapperService.getLoginLog(pageable);
    	model.addAttribute("loginLogList", loginLogList);
        return  "manager/memberInfo/memberLoginLogList";
    }
	
	
	@GetMapping("/registeredMembers")
    public String managerNewmemberSearch(Model model) {
    	System.out.println("한 달 내 신규회원 조회 페이지 이동");
    	List<Member> monthMemberList = memberMapperService.getMonthMemberList();
    	model.addAttribute("monthMemberList", monthMemberList);
    	
        return  "manager/memberInfo/registeredMembers";
    }

	
// 조회 only ▲ ---------------------------------------------------------------------------
	
	@GetMapping("/waitingForApproval")
	public String waitingForApproval(Model model) {
		System.out.println("멘토 회원가입 승인 대기 내역 조회 페이지 이동");
		List<Member> waitingForApprovalMentorList = memberMapperService.getWaitingForApprovalMentorList();
		model.addAttribute("waitingForApprovalMentorList", waitingForApprovalMentorList);
		
		return  "manager/memberInfo/waitingForApprovalList";
	}
	
	
	@GetMapping("/waitingForWithdrawal")
    public String waitingForWithdrawal(Model model) {
    	System.out.println("회원탈퇴 신청 대기 내역 페이지 이동");
        List<WithdrawalMember> waitingForWithDrawalList = memberMapperService.getWaitingForWithDrawalList();
        model.addAttribute("waitingForWithDrawalList", waitingForWithDrawalList);
    
        return  "manager/memberInfo/waitingForWithdrawalList";
	}
	
	@GetMapping("/withdrawalApprove")
	public String withdrawalApprove(@RequestParam(name="withdrawalMemberId") String withdrawalMemberId) {
		System.out.println("회원탈퇴 승인");
		memberMapperService.withdrawalApply(withdrawalMemberId);
		
		return "redirect:/manager/memberInfo/waitingForWithdrawal";
	}
	
	
	@PostMapping("/infoModify")
    public String membersInfoModify(Member member) {
		System.out.println("회원 정보 수정");
		memberMapperService.updateMemberInfoById(member);
    	
        return "redirect:/manager/member/infoModify";
    }
	
	@GetMapping("/infoModify")
	public String membersInfoModify(@RequestParam(name="memberId", required=false) String memberId,
									Pageable pageable, Model model){
		System.out.println("전체 회원 정보 수정 페이지 이동");
		PageInfo<Member> memberList = memberMapperService.getMemberList(pageable);
		model.addAttribute("memberList", memberList);
	
		if(memberId != null) {
		Member memberInfo = memberMapperService.getMemberInfoById(memberId);
		model.addAttribute("memberInfo", memberInfo);
		}
		
		return "manager/memberInfo/membersInfoModifyForm";
	}
	
	@GetMapping("/managerWithdrawal")
    public String managerWithdrawal() {
    	System.out.println("관리자 탈퇴 페이지 이동");
        return  "manager/setting/managerWithdrawal";

    }
	
	@GetMapping("/memberLevelManage")
	public String memberLevelManage(Model model) {
		System.out.println("회원 등급 수정 페이지 이동");
		List<Member> mentorList = memberMapperService.getMentorList();
		model.addAttribute("mentorList", mentorList);
		return "manager/memberInfo/memberLevelManagement";
	}

}
