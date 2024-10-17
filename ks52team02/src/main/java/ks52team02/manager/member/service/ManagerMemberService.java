package ks52team02.manager.member.service;

import java.util.List;

import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;


public interface ManagerMemberService {

	// 관리자 - 전체 회원 조회
	List<Member> getMemberList();
	
	// 관리자 - 탈퇴 회원 조회
	List<WithdrawalMember> getWithdrawalMemberList();


}
