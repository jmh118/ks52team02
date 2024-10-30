package ks52team02.manager.pay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.manager.pay.mapper.ManagerPayMapper;
import ks52team02.member.pay.dto.Pay;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/manager/pay")
public class ManagerPaymentController {
	
	private final ManagerPayMapper managerPayMapper;
	
	@GetMapping("/list")
    public String managerPaymentList(Model model) {
		
		List<Pay> payList = managerPayMapper.getPayList();
		
		model.addAttribute("payList", payList);
		model.addAttribute("title", "멘토링 결제 내역 조회");	
		
        return  "manager/pay/payList";
    }
	
	@GetMapping("/settlementList")
	public String managerSettlementRequestList(Model model) {
		
		List<PaymentSettlement> paymentSettlementList = managerPayMapper.getPaymentSettlementList(); 
		
		model.addAttribute("paymentSettlementList", paymentSettlementList);
		model.addAttribute("title", "멘토링 정산 신청 내역 조회");
		
		return  "manager/pay/settlementRequestList";
	}
	
	@GetMapping("/settlementHistoryList")
	public String managerSettlementHistoryList(Model model) {
		
		List<PaymentSettlement> paymentSettlementHistoryList = managerPayMapper.getPaymentSettlementHistoryList();
		
		model.addAttribute("paymentSettlementHistoryList", paymentSettlementHistoryList);
		model.addAttribute("title", "멘토링 정산 내역 조회");
		
		return  "manager/pay/settlementHistoryList";
	}

}
