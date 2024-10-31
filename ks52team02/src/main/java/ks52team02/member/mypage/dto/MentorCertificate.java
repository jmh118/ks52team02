package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MentorCertificate {

	private String mentorCertificateCode;
	private String mentorId;
	private String mentorCertificateNm;		//자격증 명 코드
	private String mentorInstitutionNm;		//발급기관 코드
	private String mentorIssuYmd;			//발급일
	private String mentorFileNm;			//파일명
	private String mentorGrade;				//자격증 등급

	
	
	private CertificateName certificateName;
	
}
