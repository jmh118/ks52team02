package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MenteeCertificate {

	private String menteeCtfcCode;
	private String menteeId;
	private String menteeCtfcNm;			//자격증명 
	private String menteeCtfcIstt;			//발급 기관명
	private String menteeCtfcDate;			//발급 날짜
	private String menteeCtfcReleaseYn;		//자격증 공개 유무
	private String menteeCtfcGrade;			//자격증 등급
	
	
	
	
	private CertificateName certificateName;
}
