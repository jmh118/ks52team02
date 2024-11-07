package ks52team02.member.mypage.dto;

import lombok.Data;

@Data
public class MenteePortfolio {

	private String menteePtflCode;
	private String menteeId;
	private String menteePtflTitle;			//포트폴리오 제목
	private String menteePtflCtnt;			//포트폴리오 내용
	private String menteePtflUlYn;			//포폴 업로드 유무
	private String menteePtflRgstDate;		//포폴 등록일
	private String menteePtflReleaseYn;		//공개 유무
	private String menteePtflFileNm;		//업로드 파일명
	
}
