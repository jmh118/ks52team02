package ks52team02.member.mentoring.dto;

import lombok.Data;

@Data
public class Notice {
	private String noticeCode;
	private String topicCode;
	private String memberId;
	private String memberName;
	private String noticeTitle;
	private String noticeContent;
	private String noticeStartTime;
	private String noticeEndTime;
	private String noticeStartYmd;
	private String noticeEndYmd;
	private int noticeUntprc;
	private String topicName;
	private String mentorPowkName;
	private String mentoringPeriod;
	private String honorMentor;
	
}
