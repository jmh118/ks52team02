package ks52team02.member.pay.service;

import java.util.List;

import ks52team02.member.pay.dto.Pay;

public interface MemberPayService {
	
	// 정산 신청
	boolean addSettlementApply(String payCode, String noticeCode, String memberId);
	
	// 신청한 정산 내역이 있는지 조회 
	List<Boolean> isCheckSettlement(List<Pay> paymentList);
	
	// 정산신청 내역 개수로 true, false 판단
	boolean isSettlementCntByPayCode(String payCode);
	
	// (멘토) 신청받은 결제 내역 조회 
	List<Pay> getPaymentListByMentorId(String memberId);
	
	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
	
	// (멘티) 결제 내역 조회
	List<Pay> getMenteePaymentListById(String memberId);
}
