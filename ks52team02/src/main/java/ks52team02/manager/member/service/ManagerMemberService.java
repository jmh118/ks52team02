package ks52team02.manager.member.service;

import java.util.List;
import java.util.Map;

import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;


public interface ManagerMemberService {
	
	// 월별 로그인 건수 조회
	List<Integer> getMonthlyLoginCounts();
	
	// 월별 회원가입 건수 조회
	List<Integer> getMonthlyRegisterCounts();

	// 멘토 권한 멤버 조회
	PageInfo<Member> getMentorList(Pageable pageable);

	// 관리자 - 전체 회원 조회
	PageInfo<Member> getMemberList(Pageable pageable, String keyword);
	
	// 관리자 - 휴면 회원 조회
	PageInfo<Member> getDormantMemberList(Pageable pageable, String keyword);
	
	// 관리자 - 탈퇴 회원 조회
	PageInfo<WithdrawalMember> getWithdrawalMemberList(Pageable pageable, String keyword);
	
	// 관리자 - 회원탈퇴 승인
	int withdrawalApply(WithdrawalMember withdrawalMember);
	
	// 관리자 - 관리자 탈퇴
	int managerWithdrawalApply(WithdrawalMember withdrawalManager);

	// 관리자 - 회원탈퇴 승인 후 처리 
	int delMember(WithdrawalMember withdrawalMember);

	// 관리자 - 멤버 로그인 로그 조회
	PageInfo<LoginLog> getLoginLog(Pageable pageable, String keyId, String keyLoginCode, String memberLevelCate, String loginLogStartDate, String loginLogEndDate);

	// 관리자 - 한 달 내 가입 회원 조회
	PageInfo<Member> getMonthMemberList(Pageable pageable, String keyword);

	// 관리자 - 탈퇴 대기 회원 조회
	PageInfo<WithdrawalMember> getWaitingForWithDrawalList(Pageable pageable, String keyword);

	// ID로 특정 회원 조회
	Member getMemberInfoById(String memberId);

	// 회원 정보 수정
	void updateMemberInfoById(Member member);
	
	// 승인 요청 멘토 조회
	PageInfo<Member> getWaitingForApprovalMentorList(Pageable pageable, String keyword);

	// 멘토 승인
	int approvalMentorLevel(MentorApproval mentorApproval, String actionType);

	// 멘토 권한으로 변경
	int changeMentorLevel(MentorApproval mentorApproval);


}
