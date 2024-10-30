package ks52team02.member.pay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.service.MemberPayService;
import ks52team02.member.review.service.MemberReviewService;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/pay")
public class MemberPayController {
	
	private final MemberPayService memberPayService;
	private final MemberReviewService memberReviewService;
	
	
	@GetMapping("/list")
	public String getPayList(HttpSession session, Model model) {
		
		String memberId = (String) session.getAttribute("SID");
		List<Pay> paymentList = memberPayService.getMenteePaymentListById(memberId);
		List<Boolean> isCheck = memberReviewService.isCheckReview(paymentList);
		
		model.addAttribute("activeMenu", "payList");
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("reviewCheck", isCheck);
		
		return "member/pay/payList.html";
	}
	
	@GetMapping("/success")
	public String paymentStatusIsSuccessView() {
		System.out.println("결제 성공 시 화면");
		return "member/pay/payStatusSuccess";
	}
	
	@GetMapping("/fail")
	public String paymentStatusIsFailView() {
		System.out.println("결제 실패 시 화면");
		return "member/pay/payStatusFail";
	}
		
	@GetMapping("/settlementList")
	public String getAdjustmentList(Model model) {
		
		model.addAttribute("activeMenu", "settlementList");
		
		System.out.println("정산내역 조회 화면");
		return "member/pay/settlementList";
	}
	
	
	
	

}
