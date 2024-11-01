package ks52team02.member.mentoring.dto;

import java.util.List;

import lombok.Data;

@Data
public class NoticeDetail {
	
	private String noticeDetailCode;
	private String noticeCode;
	private List<String> mentoringTimeList;
	private List<String> mentoringYmdList;
	
	private String mentoringTime;
	private String mentoringYmd;
	

}
