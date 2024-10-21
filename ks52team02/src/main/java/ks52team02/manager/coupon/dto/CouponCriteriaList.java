package ks52team02.manager.coupon.dto;

import lombok.Data;

@Data
public class CouponCriteriaList {
	
	private String menteeId;
    private String goalSettingCode;
    private int acmlCnt;
    private String couponName;
    private int couponAmount;
	
}
