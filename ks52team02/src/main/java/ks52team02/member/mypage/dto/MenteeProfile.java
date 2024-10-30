package ks52team02.member.mypage.dto;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentoring.dto.Notice;
import lombok.Data;

@Data
public class MenteeProfile {
	
	private String profileCode;
	private String memberId;
	private String interestField;
	private String fieldTechnology;
	private String experienceLevel;
	private String experienceExplanation;
	private String releaseYN;
	
	
	//private String memberName;
	//private String mentoringName;
	private Member member;
	private Notice notice;
}
