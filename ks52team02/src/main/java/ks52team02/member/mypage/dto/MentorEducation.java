package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MentorEducation {

	private String mentorEducationCode;
	private String mentorId;
	private String mentorAcbgUp;		//회종학력유무
	private float mentorCredits;		//학점
	private String mentorSchoolNm;		//학교명
	private String mentorScsbjtNm;		//전공
	private String mentorSection;		// 졸업/수료 구분
	private String mentorFileNm;		//파일명
	private String mentorMtctYmd;		//입학 날짜
	private String mentorGdtYmd;		//졸업 날짜
}





