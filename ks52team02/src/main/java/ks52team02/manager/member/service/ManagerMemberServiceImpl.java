package ks52team02.manager.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.manager.member.mapper.ManagerMemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
		
		return null;
	}
}