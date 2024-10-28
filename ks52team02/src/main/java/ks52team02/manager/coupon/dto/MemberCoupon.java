package ks52team02.manager.coupon.dto;

import lombok.Data;

@Data
public class MemberCoupon {
	
	private String memberCouponCode;
	private String couponCriteriaCode;
	private String memberId;
	private String goalCode;
	private String couponAmount;
	private String couponUseYN;
	private String couponRdgDate;

	private CouponCriteria couponCriteria;
}
