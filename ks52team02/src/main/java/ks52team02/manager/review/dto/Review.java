package ks52team02.manager.review.dto;

import lombok.Data;

@Data
public class Review {
	
	private String reviewCode;
	private String menteeId;
	private String settlementCalCode;
	private String reviewContent;
	private double reviewScore;
	private String reviewRegDate;

}
