package ks52team02.member.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coupon")
public class MemberCouponController {

	@GetMapping("/list")
    public String MoveMenteeCouponList() {
        System.out.println("쿠폰 내역 | 멘토 쿠폰 보유 내역 조회 화면");
        return  "member/coupon/cuponList";
    }
}
