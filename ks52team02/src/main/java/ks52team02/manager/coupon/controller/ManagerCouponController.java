package ks52team02.manager.coupon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.coupon.dto.MemberCoupon;
import ks52team02.manager.coupon.mapper.ManagerCouponMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/coupon")
public class ManagerCouponController {
	
	private final ManagerCouponMapper manageCouponMapper;
	
	@GetMapping("/criteriaList")
    public String managerCoupon() {
    	System.out.println("관리자 쿠폰 관리 화면");
        return  "manager/coupon/couponCriteriaList";
    }
	
	@GetMapping("/list")
	public String managerCouponList(Model model) {
		List<MemberCoupon> couponList = manageCouponMapper.getMenteeCouponList();
		log.info("couponList : {}", couponList);
		
		model.addAttribute("couponList", couponList);
		
		log.info("관리자 쿠폰 내역 조회 화면 이동");
		return  "manager/coupon/couponList";
	}
	

}
