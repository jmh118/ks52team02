package ks52team02.manager.pay.dto;

import lombok.Data;

@Data
public class PaymentSettlement {

	private String paymentSettlementCode;
	private String noticeCode;
	private String paySettlementCalCode;
	private String mentorId;
	private String menteeId;
	private String paymentSettlementDemandDate;
	private String paymentSettlementGiveDate;
	private String paymentSettlementStatus;
	private String managerId;
	
	private Pay pay;
}
