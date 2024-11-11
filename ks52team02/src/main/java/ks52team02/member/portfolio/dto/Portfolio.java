package ks52team02.member.portfolio.dto;

import lombok.Data;

@Data
public class Portfolio {
	
	
	
	private String portfolioCode; 
	private String portfolioId; //포폴 작성자 아이디
	private String portfolioTitle;
	private String portfolioContent;
	private String portfolioDate; //포폴 등록일
	private String portfolioYn; //포폴 공개 유무
	private String portfolioFile; //포폴 파일명
	
}
