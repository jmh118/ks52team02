package ks52team02.member.pay.dto;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeDetail;
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
	
	private Notice notice;
	private NoticeDetail noticeDetail;
	
}
