package ks52team02.member.login.service;

import java.util.Map;


public interface MemberLoginService {
	
	
	
	// 탈퇴 검증으로 로그인 유무 반환
	boolean memberWithdrawalStatus(String memberId);
	
	// 아이디로 권한 확인 
	boolean isCheckMemberLevel(String memberId);
	
	// 비밀번호 확인 
	boolean isCheckMemberPw(String memberId, String memeberPw);
	
	// 회원여부 확인
	Map<String, Object> checkedMember(String memberId, String memberPw);
	
	// 아이디로 비밀번호 찾기
	String findMemberPwById(String inputId);

}
