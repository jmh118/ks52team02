package ks52team02.member.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class MemberPayController {
	
	
	@GetMapping("/list")
	public String getPayList() {
		System.out.println("결제 내역 조회 화면");
		return "member/pay/payList.html";
	}
	
	@GetMapping("/success")
	public String paymentStatusIsSuccessView() {
		System.out.println("결제 성공 시 화면");
		return "member/pay/paymentStatusSuccess";
	}
	
	@GetMapping("/fail")
	public String paymentStatusIsFailView() {
		System.out.println("결제 실패 시 화면");
		return "member/pay/paymentStatusFail";
	}
		
	@GetMapping("/settlementList")
	public String getAdjustmentList() {
		System.out.println("정산내역 조회 화면");
		return "member/pay/settlementList";
	}
	
	
	
	

}
