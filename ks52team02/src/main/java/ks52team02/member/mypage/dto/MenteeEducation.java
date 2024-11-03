package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MenteeEducation {

	private String menteeAcbgCode;
	private String menteeId;
	private String menteeSchoolNm;		//학교명
	private String menteeMajorNm;		//전공(학과)명
	private float menteeSchoolCd;		//학점
	private String menteeGdtCd;			//졸업 학점
	private String menteeReleaseYn;		//공개 유무
	private String menteeRegDate;		//학력 등록일자
	private String menteeMtcltDate;		//입학 일자
	private String menteeGdtDate;		//졸업 일자
	
	
	
	
}
