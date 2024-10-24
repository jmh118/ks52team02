package ks52team02.member.login.service;

import java.util.Map;



public interface MemberLoginService {
	
	// 비밀번호 확인 
	boolean isCheckMemberPw(String memberId, String memeberPw);
	
	
	// 회원여부 확인
	Map<String, Object> checkedMember(String memberId, String memberPw);

}
