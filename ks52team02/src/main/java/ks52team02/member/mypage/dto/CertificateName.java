package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class CertificateName {

	private String nameCode;
	private String certificateNm;	//자격증 명
	private String certificateCf;	//자격증 분류
	private String institutionNm;	//발급기관
	
}
