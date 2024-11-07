package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class CertificateName {

	private String nameCode;		//자격증 코드
	private String certificateNm;	//자격증 이름
	private String certificateCf;	//자격증 분류
	private String institutionCode;	//발급기관 코드
	private String institutionNm;	//발급기관 이름
	
}
