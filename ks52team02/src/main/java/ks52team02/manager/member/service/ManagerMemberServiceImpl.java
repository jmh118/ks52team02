package ks52team02.manager.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
