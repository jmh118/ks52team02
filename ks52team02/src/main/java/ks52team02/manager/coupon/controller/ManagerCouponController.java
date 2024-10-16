package ks52team02.manager.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/coupon")
public class ManagerCouponController {
	
	@GetMapping("/criteriaList")
    public String managerCoupon() {
    	System.out.println("관리자 쿠폰 관리 화면");
        return  "manager/coupon/couponCriteriaList";
    }
	
	@GetMapping("/list")
	public String managerCouponList() {
		System.out.println("관리자 쿠폰 내역 조회 화면 이동");
		return  "manager/coupon/couponList";
	}
	

}
