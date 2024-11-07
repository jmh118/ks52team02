package ks52team02.manager.review.dto;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeDetail;
import lombok.Data;

@Data
public class Review {
	
	private String reviewCode;
	private String menteeId;
	private String settlementCalCode;
	private String reviewContent;
	private double reviewScore;
	private String reviewRegDate;
	
	private Notice notice;
	private NoticeDetail noticeDetail;

}
