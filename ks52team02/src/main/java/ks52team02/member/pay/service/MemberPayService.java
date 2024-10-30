package ks52team02.member.pay.service;

import java.util.List;

import ks52team02.member.pay.dto.Pay;

public interface MemberPayService {
	
	
	
	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
	
	// 결제 내역 조회
	List<Pay> getMenteePaymentListById(String memberId);
}
