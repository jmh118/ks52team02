package ks52team02.manager.coupon.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.coupon.dto.CouponCriteriaList;
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
    public String managerCoupon(Model model) {
		
		List<CouponCriteriaList> couponCriteriaList = manageCouponMapper.getMenteeCouponCriteriaList();
		
		model.addAttribute("couponCriteriaList", couponCriteriaList);
		model.addAttribute("title", "쿠폰 기준 조회 및 지급");
		model.addAttribute("content", "멘티의 쿠폰 기준 (목표 설정별 블로그 개수) 충족 시 쿠폰 지급 버튼으로 해당 쿠폰 지급");
		
        return  "manager/coupon/couponCriteriaList";
    }
	
	@GetMapping("/list")
	public String managerCouponList(Model model) {
		List<MemberCoupon> couponList = manageCouponMapper.getMenteeCouponList();
		
		model.addAttribute("couponList", couponList);
		model.addAttribute("title", "멘티의 쿠폰 보유 내역");
		model.addAttribute("content", "멘티의 쿠폰 보유 내역 조회합니다.");

		return  "manager/coupon/couponList";
	}
	

}
