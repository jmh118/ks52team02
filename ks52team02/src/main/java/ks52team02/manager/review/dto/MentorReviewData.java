package ks52team02.manager.review.dto;

import ks52team02.manager.member.dto.Member;
import lombok.Data;

@Data
public class MentorReviewData {
	
	private String mentorReviewAvgCntCode;
	private String mentorId;
	private int mentorReviewCnt;
	private double mentorReviewAvg;
	private String firstRegDate;
	private String lastUpDate;
	private char isHonorMentor;
	private boolean isapprove;
	private boolean isCancel;
	

}
