package ks52team02.member.pay.service;

public interface MemberPayService {
	
	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
}
