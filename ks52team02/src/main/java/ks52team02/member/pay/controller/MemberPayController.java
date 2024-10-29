package ks52team02.member.pay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.pay.dto.MemberPay;
import ks52team02.member.pay.mapper.MemberPayMapper;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/pay")
public class MemberPayController {
	
	private final MemberPayMapper memberPayMapper;
	
	@GetMapping("/list")
	public String getPayList(HttpSession session, Model model) {
		
		String memberId = (String) session.getAttribute("SID");
		List<MemberPay> paymentList = memberPayMapper.getMenteePaymentListById(memberId);
		
		model.addAttribute("activeMenu", "payList");
		model.addAttribute("paymentList", paymentList);
		
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
