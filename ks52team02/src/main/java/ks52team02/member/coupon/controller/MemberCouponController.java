package ks52team02.member.coupon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.coupon.dto.Coupon;
import ks52team02.member.coupon.mapper.MemberCouponMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class MemberCouponController {
	
	private final MemberCouponMapper memberCouponMapper; 

	@GetMapping("/list")
    public String MoveMenteeCouponList(HttpSession session, Model model) {
        
		String memberId = (String) session.getAttribute("SID");
		List<Coupon> couponList = memberCouponMapper.getMenteeCouponListById(memberId);
		
		log.info("잘받??? couponList : {}", couponList);
		
		model.addAttribute("couponList", couponList);
		
        return  "member/coupon/cuponList";
    }
}
