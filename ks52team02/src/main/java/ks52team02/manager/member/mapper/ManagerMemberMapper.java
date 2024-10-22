package ks52team02.manager.member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;

@Mapper
public interface ManagerMemberMapper {
	
	// 멘토 권한 멤버 조회
	List<Member> getMentorList();

	// 관리자 - 전체 회원 조회
	List<Member> getMemberList();
	
	// 관리자 - 휴면 회원 정보 조회
	List<Member> getDormantMemberList();

	// 관리자 - 탈퇴 회원 조회	
	List<WithdrawalMember> getWithdrawalMemberList();
	
	// 관리자 - 로그인 로그 조회
	List<LoginLog> getLoginLog();

	
}
