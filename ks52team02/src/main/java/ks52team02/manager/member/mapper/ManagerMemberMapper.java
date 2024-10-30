package ks52team02.manager.member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

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

	// 관리자 - 한 달 내 가입한 멤버 조회
	List<Member> getMonthMemberList();

	// 관리자 - 탈퇴 대기 회원 조회
	List<WithdrawalMember> getWaitingForWithDrawalList();

	// ID로 특정 회원 정보 조회
	Member getMemberInfoById(String memberId);

	// 회원 정보 수정
	void updateMemberInfoById(Member member);
	
	
}
