package ks52team02.member.pay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.service.MemberPayService;
import ks52team02.member.review.service.MemberReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/pay")
public class MemberPayController {
	
	private final MemberPayService memberPayService;
	private final MemberReviewService memberReviewService;
	
	@PostMapping("settlementApply")
	@ResponseBody
	public boolean settlementApply(@RequestParam("payCode") String payCode, @RequestParam("noticeCode") String noticeCode, HttpSession session) {
		
		String memberId = (String) session.getAttribute("SID");
		
		boolean isApply = memberPayService.addSettlementApply(payCode, noticeCode, memberId);
		
		return isApply;
	}
	
	@GetMapping("/settlementList")
	public String getsettlementList(HttpSession session, Model model) {
		
		String memberId = (String) session.getAttribute("SID");
		List<Pay> paymentList = memberPayService.getPaymentListByMentorId(memberId);
		List<Boolean> isCheck = memberPayService.isCheckSettlement(paymentList);
		
		log.info("isCheck : {}", isCheck);
		
		model.addAttribute("activeMenu", "settlementList");
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("settlementCheck", isCheck);
		
		return "member/pay/settlementList";
	}
	
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

}
