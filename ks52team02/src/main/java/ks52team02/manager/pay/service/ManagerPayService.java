package ks52team02.manager.pay.service;


import java.util.List;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.member.pay.dto.Pay;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface ManagerPayService {
	
	// 월별 결제 건수 조회
	List<Integer> getMonthlyPaymentCounts(); 
	
	// 멘토 정산 승인
	int managerPayApproveById(String settlementCode, String managerId);
	
	// 멘토 정산 내역 조회
	PageInfo<PaymentSettlement> getPaymentSettlementHistoryList(Pageable pageable);
	
	// 멘토 정산 신청 내역 조회
	PageInfo<PaymentSettlement> getPaymentSettlementList(Pageable pageable);

	// 멘티 결제 내역 조회
	PageInfo<Pay> getMenteePayList(Pageable pageable);
}
