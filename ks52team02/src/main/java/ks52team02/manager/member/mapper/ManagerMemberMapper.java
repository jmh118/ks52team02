package ks52team02.manager.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.manager.member.dto.WithdrawalMember;

@Mapper
public interface ManagerMemberMapper {
	
	// 월별 로그인 건수 조회
	List<Map<String, Object>> getMonthlyLoginCnt();
	// 월별 회원가입 건수 조회
	List<Map<String, Object>> getMonthlyRegisterCnt();
	
	// 전체 회원 수 조회
	int getAllMemberCnt();
	
	// 멘토 권한 멤버 조회
	// 멘토 권한 멤버수 조회
	List<Member> getMentorList(Map<String, Object> paramMap);
	int getMentorListCount();
	
	// 관리자 - 전체 회원 조회
	// 관리자 - 전체 회원 수 조회
	List<Member> getMemberList(Map<String, Object> paramMap);
	int getMemberListCount(String keyword);
	
	
	// 관리자 - 휴면 회원 정보 조회
	// 관리자 - 휴면 회원 수 조회
	List<Member> getDormantMemberList(Map<String, Object> paramMap);
	int getDormantMemberListCount(String keyword);

	
	// 관리자 - 탈퇴 회원 조회
	// 관리자 - 탈퇴 회원 수 조회
	List<WithdrawalMember> getWithdrawalMemberList(Map<String, Object> paramMap);
	int getWithdrawalMemberListCount(String keyword);

	
	// 관리자 - 회원탈퇴 승인
	int withdrawalApply(WithdrawalMember withdrawalMember);
	
	
	// 관리자 - 관리자 탈퇴 승인
	int managerWithdrawalApply(WithdrawalMember withdrawalMember);

	
	// 관리자 - 회원탈퇴 승인 후 처리
	int delMember(WithdrawalMember withdrawalMember);
	
	
	// 관리자 - 로그인 로그 조회
	// 관리자 - 로그인 로그 수 조회
	List<LoginLog> getLoginLog(Map<String, Object> paramMap);
	int getLoginLogCount(String keyId, String keyLoginCode, String memberLevelCate, String loginLogStartDate, String loginLogEndDate);

	
	// 관리자 - 한 달 내 가입한 멤버 조회
	// 관리자 - 한 달 내 가입한 멤버 수 조회
	List<Member> getMonthMemberList(Map<String, Object> paramMap);
	int getMonthMemberListCount();

	
	// 관리자 - 탈퇴 대기 회원 조회
	List<WithdrawalMember> getWaitingForWithDrawalList();

	
	// ID로 특정 회원 정보 조회
	Member getMemberInfoById(String memberId);

	
	// 회원 정보 수정
	void updateMemberInfoById(Member member);
	
	
	// 승인 요청 멘토 조회
	List<Member> getWaitingForApprovalMentorList();

	
	// 멘토 요청 승인/반려
	int approvalMentorLevel(MentorApproval mentorApproval);

	
	// 멘토 권한으로 변경
	int changeMentorLevel(MentorApproval mentorApproval);



}
