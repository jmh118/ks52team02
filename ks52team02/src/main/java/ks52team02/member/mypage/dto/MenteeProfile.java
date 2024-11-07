package ks52team02.member.mypage.dto;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentoring.dto.Notice;
import lombok.Data;

@Data
public class MenteeProfile {
	
	private String profileCode;
	private String memberId;
	private String interestField;			//관심분야
	private String fieldTechnology;			//관심분야 필요기술
	private String experienceLevel;			//관심분야 경험 수준
	private String experienceExplanation;	//관심분야 경험 설명
	private String releaseYN;				//소개글 공개 유무
	
	
	//private String memberName;
	//private String mentoringName;
	private Member member;
	private Notice notice;
}
