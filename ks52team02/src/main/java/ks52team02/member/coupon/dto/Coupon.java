package ks52team02.member.coupon.dto;

import lombok.Data;

@Data
public class Coupon {
	
	private String couponCode;
	private String goalName; 
	private int goalRecordCnt;
	private String criteriaCouponCode; 
	private String memberId; 
	private String goalSettingCode; 
	private int couponAmount; 
	private char couponUseYN; 
	private String couponRegDate;
	
	
	
}
