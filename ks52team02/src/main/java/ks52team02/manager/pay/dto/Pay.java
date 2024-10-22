package ks52team02.manager.pay.dto;

import lombok.Data;

@Data
public class Pay {

	private String paySettlementCalCode;
	private String noticeCode;
	private String menteeCouponStatusCode;
	private String payId;
	private int payAmountBeforeDiscount;
	private int menteeCoupon;
	private int totalAmount;
	private String payDate;
	private String payMethod;
	private int payFee;
	private int mentorCalAmount;
	private int flatformCalAmount;
	private String mentorCalSituation;
	private String mentorCalCompletionDate;
	private String mentoringProcStatus;
	
}
