package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MentorWork {

	private String mentorWorkCode;
	private String mentorId;
	private String mentorPowkNm; 	//회사명
	private String mentorTask;		//담당업무
	private String mentorJncmpYmp;	//입사날짜
	private String mentorRsgntnYmp;	//퇴사날짜
	private int mentorCnt;		//근무기간
	private String mentorStatus;	//재직 여부
	private String mentorFileNm;	//파일명
	
	
}
