package ks52team02.manager.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.member.pay.dto.Pay;
import ks52team02.page.Pageable;

@Mapper
public interface ManagerPayMapper {
	
	// 총 수익 조회
	int getFlatformCalAmount();
	
	// 총 정산된 금액 조회
	int getPaymentSettlementAmount();
	
	// 총 결제 금액 조회
	int getTotalPayAmount();
	
	// 멘토 정산 승인
	int managerPayApproveById(String settlementCode, String managerId);
	
	// 정산 정산 내역 행 개수 조회
	int getPaymentSettlementHistoryCnt();
	
	// 멘토 정산 내역 조회
	List<PaymentSettlement> getPaymentSettlementHistoryList(Pageable pageable);
	
	// 정산 신청 내역 행 개수 조회
	int getPaymentSettlementCnt();
	
	// 멘토 정산 신청 내역 조회
	List<PaymentSettlement> getPaymentSettlementList(Pageable pageable);
	
	// 결제 내역 행 개수 조회
	int getPayListCount();
	
	// 멘티 결제 내역 조회
	List<Pay> getPayList(Pageable pageable);

}
