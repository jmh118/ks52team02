package ks52team02.member.login.service;

import java.util.Map;

public interface MemberLoginService {
	
	// 회원여부 확인
	Map<String, Object> checkedMember(String memberId, String memberPw);

}