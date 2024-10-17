package ks52team02.member.mentoring.dto;

import lombok.Data;

@Data
public class Notice {
	private String noticeCode;
	private String topicCode;
	private String memberId;
	private String noticeTitle;
	private String noticeContent;
	private String noticeStartTime;
	private String noticeEndTime;
	private String noticeStartYmd;
	private String noticeEndYmd;
	private int noticeUntprc;
	
	
}
