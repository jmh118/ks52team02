package ks52team02.manager.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.manager.pay.mapper.ManagerPayMapper;
import ks52team02.member.pay.dto.Pay;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ManagerPayServiceImpl implements ManagerPayService {

	private final ManagerPayMapper managerPayMapper; 
	
	
	
	@Override
	public int managerPayApproveById(String settlementCode) {
		
		return managerPayMapper.managerPayApproveById(settlementCode);
	}
	
	
	@Override
	public PageInfo<PaymentSettlement> getPaymentSettlementHistoryList(Pageable pageable) {
		
		int rowCnt = managerPayMapper.getPaymentSettlementHistoryCnt();

		List<PaymentSettlement> contents = managerPayMapper.getPaymentSettlementHistoryList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	
	@Override
	public PageInfo<PaymentSettlement> getPaymentSettlementList(Pageable pageable) {
		
		int rowCnt = managerPayMapper.getPaymentSettlementCnt();
		
		List<PaymentSettlement> contents = managerPayMapper.getPaymentSettlementList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	
	@Override
	public PageInfo<Pay> getMenteePayList(Pageable pageable) {
		
		int rowCnt = managerPayMapper.getPayListCount();
		
		List<Pay> contents = managerPayMapper.getPayList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}




}