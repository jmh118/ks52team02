package ks52team02.member.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.withdrawal.dto.WithdrawalStatus;

@Mapper
public interface MemberLoginMapper {
	
	// 탈퇴 상태 조회
	WithdrawalStatus getMemberWithdrawalStatus(String memberId);
	
	// 로그인 성공 시 로그인 이력 등록 
	int addLoginLog(String loginCode, String memberId, String memberLevelCode);
	
	// 관리자 로그인 화면에서 관리자 아이디 검증을 위한 권한 조회
	String getMemberLevelById(String inputId);
	
	// 회원 여부 확인
	Member getMemberInfoById(String inputId);

	// 아이디로 비밀번호 찾기
	String findMemberPwById(String inputId);

}
