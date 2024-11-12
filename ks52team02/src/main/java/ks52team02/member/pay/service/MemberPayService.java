package ks52team02.member.pay.service;

import java.util.List;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.member.pay.dto.BeforePay;
import ks52team02.member.pay.dto.MentoringData;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.dto.SearchFilter;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface MemberPayService {
	
	// (멘토) 정산 내역 - 연도, 월 검색로 조회
	List<PaymentSettlement> searchSettlementHistoryList(String memberId, SearchFilter searchFilter);
	
	// (멘토) 정산 내역 조회
	PageInfo<PaymentSettlement> getSettlementHistoryList(String memberId, Pageable pageable);
	
	// 연도,월 검색 결제 내역 조회
	List<Pay> getFilterMemberPaymentListById(String memberId, String memberLevel, SearchFilter searchFilter);
	
	// 결제 전 화면에서 신청 취소 
	void removeMentoringApplyByCode(String applyCode, String detailCode);
	
	// 결제 추가 
	int addPay(List<MentoringData> mentoringDataList);
	
	// 멘토링 미결제건 개수 조회
	int getBeforePayCnt(String memberId); 
	
	// 결제 전 멘토링 신청 목록 조회
	List<BeforePay> getBeforePayListById(String memberId);
	
	// 정산 신청
	boolean addSettlementApply(String payCode, String noticeCode, String memberId);
	
	// 신청한 정산 내역이 있는지 조회 
	List<Boolean> isCheckSettlement(PageInfo<Pay> paymentList);
	
	// 정산신청 내역 개수로 true, false 판단
	boolean isSettlementCntByPayCode(String payCode);
	
	// (멘토) 신청받은 결제 내역 조회 
	PageInfo<Pay> getPaymentListByMentorId(String memberId, Pageable pageable);
	
	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
	
	// (멘티) 결제 내역 조회
	PageInfo<Pay> getMenteePaymentListById(String memberId, Pageable pageable);
}
