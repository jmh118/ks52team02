package ks52team02.member.pay.dto;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeDetail;
import lombok.Data;

@Data
public class BeforePay {
	
	private String applyCode;
	private String noticeCode;
	private String noticeDetailCode;
	private String memberId;
	private String amountStatus;
	private String updateYmd;
	
	private Notice notice;
	private NoticeDetail noticeDetail;
	

}
