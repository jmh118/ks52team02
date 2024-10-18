package ks52team02.manager.coupon.dto;

import lombok.Data;

@Data
public class MemberCoupon {
	
	private String memberId;
	private String couponName;
	private String couponAmount;
	private String couponUseYN;
	private String couponRdgDate;

}
