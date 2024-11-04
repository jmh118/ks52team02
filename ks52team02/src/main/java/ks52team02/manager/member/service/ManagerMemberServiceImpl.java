package ks52team02.manager.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.manager.member.mapper.ManagerMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ManagerMemberServiceImpl implements ManagerMemberService {

	private final ManagerMemberMapper managerMemberMapper;

	@Override
	public List<Member> getMemberList(){
		List<Member> memberList = managerMemberMapper.getMemberList();
		
		return memberList;
	}

	@Override
	public List<WithdrawalMember> getWithdrawalMemberList() {
		List<WithdrawalMember> withdrawalMemberList = managerMemberMapper.getWithdrawalMemberList();
		
		return withdrawalMemberList;
	}

	@Override
	public List<Member> getDormantMemberList() {
		List<Member> dormantMemberList = managerMemberMapper.getDormantMemberList();
		
		return dormantMemberList;
	}
	
	@Override
	public List<LoginLog> getLoginLog(){
		List<LoginLog> loginLogList = managerMemberMapper.getLoginLog();
		
		return loginLogList;
	}

	@Override
	public List<Member> getMentorList() {
		List<Member> mentorList = managerMemberMapper.getMentorList();
		
		return mentorList;
	}

	@Override
	public List<Member> getMonthMemberList() {
		List<Member> monthMemberList = managerMemberMapper.getMonthMemberList();

		return monthMemberList;
	}

	@Override
	public List<WithdrawalMember> getWaitingForWithDrawalList() {
		List<WithdrawalMember> waitingForWithDrawalList = managerMemberMapper.getWaitingForWithDrawalList();
		
		return waitingForWithDrawalList;
	}

	@Override
	public void updateMemberInfoById(Member member) {
		managerMemberMapper.updateMemberInfoById(member);
	}

	@Override
	public Member getMemberInfoById(String memberId) {
		Member memberInfo = managerMemberMapper.getMemberInfoById(memberId);

		return memberInfo;
	}

	@Override
	public List<Member> getWaitingForApprovalMentorList() {
		List<Member> waitingForApprovalMentorList = managerMemberMapper.getWaitingForApprovalMentorList();
		
		return waitingForApprovalMentorList;
	}

	@Override
	public int withdrawalApply(String withdrawalMemberId) {
		
		return managerMemberMapper.withdrawalApply(withdrawalMemberId);
	}
	
	
	

}

