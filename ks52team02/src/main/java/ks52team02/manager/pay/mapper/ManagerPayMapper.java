package ks52team02.manager.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.pay.dto.Pay;
import ks52team02.manager.pay.dto.PaymentSettlement;

@Mapper
public interface ManagerPayMapper {
	
	// 멘토 정산 내역 조회
	List<PaymentSettlement> getPaymentSettlementHistoryList();
	
	// 멘토 정산 신청 내역 조회
	List<PaymentSettlement> getPaymentSettlementList();
	
	// 멘티 결제 내역 조회
	List<Pay> getPayList();

}