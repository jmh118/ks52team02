package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MentorProject {

	private String mentorProjectCode;
	private String mentorWorkCode;
	private String mentorId;
	private String mentorProjectNm; 	//프로젝트명
	private String mentorBgngYmd;		//플젝 시작 날짜
	private String mentorCmptnYmd;		//플젝 완료 날짜
	private String mentorCn;			//수행 업무
	private String mentorFileNm;		//파일명
	
}
