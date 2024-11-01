package ks52team02.manager.pay.service;

import ks52team02.member.pay.dto.Pay;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface ManagerPayService {
	
	// 멘토 정산 승인
	int managerPayApproveById(String settlementCode);

	// 멘티 결제 내역 조회
	PageInfo<Pay> getMenteePayList(Pageable pageable);
}
