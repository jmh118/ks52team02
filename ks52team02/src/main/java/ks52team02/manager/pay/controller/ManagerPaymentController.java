package ks52team02.manager.pay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.manager.pay.mapper.ManagerPayMapper;
import ks52team02.manager.pay.service.ManagerPayService;
import ks52team02.member.pay.dto.Pay;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/manager/pay")
public class ManagerPaymentController {
	
	private final ManagerPayMapper managerPayMapper;
	private final ManagerPayService managerPayService;
	
	@GetMapping("/approve")
	public String managerPayApprove(@RequestParam(name="settlementCode") String settlementCode) {
		
		managerPayService.managerPayApproveById(settlementCode);
		
		return "redirect:/manager/pay/settlementList";
	}
	
	@GetMapping("/list")
    public String managerPaymentList(Pageable pageable, Model model) {
		
		PageInfo<Pay> payList = managerPayService.getMenteePayList(pageable);
		
		model.addAttribute("payList", payList);
		model.addAttribute("title", "멘토링 결제 내역 조회");	
		
        return  "manager/pay/payList";
    }
	
	@GetMapping("/settlementList")
	public String managerSettlementRequestList(Pageable pageable, Model model) {
		
		PageInfo<PaymentSettlement> paymentSettlementList = managerPayService.getPaymentSettlementList(pageable);
		
		model.addAttribute("paymentSettlementList", paymentSettlementList);
		
		return  "manager/pay/settlementRequestList";
	}
	
	@GetMapping("/settlementHistoryList")
	public String managerSettlementHistoryList(Pageable pageable, Model model) {
		
		PageInfo<PaymentSettlement> paymentSettlementHistoryList = managerPayService.getPaymentSettlementHistoryList(pageable);
		
		model.addAttribute("paymentSettlementHistoryList", paymentSettlementHistoryList);
		model.addAttribute("title", "멘토링 정산 내역 조회");
		
		return  "manager/pay/settlementHistoryList";
	}

}
