package ks52team02.member.pay.dto;

import lombok.Data;

@Data
public class MemberPay {
	
	private String payCode;
	private String noticeTitle;
	private String memberId;
	private int payAmount;
	private String paymentDate;
	private String mentoringStatus;

}
