package ks52team02.member.mentoring.dto;

import lombok.Data;

@Data
public class NoticeQuestion {
	
	private String questionCode;
	private String noticeCode;
	private String memberId;
	private String questionContent;
	private String questionYmd;
	

	private NoticeAnswer noticeAnswer;

}
