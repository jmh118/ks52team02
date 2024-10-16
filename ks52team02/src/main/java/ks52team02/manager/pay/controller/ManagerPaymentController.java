package ks52team02.manager.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/manager/pay")
public class ManagerPaymentController {
	
	
	@GetMapping("/list")
    public String managerPaymentList() {
    	System.out.println("관리자 결제 조회 페이지 이동");
        return  "manager/pay/payList";
    }
	
	@GetMapping("/settlementList")
	public String managerSettlementRequestList() {
		System.out.println("관리자 정산 신청 내역 페이지 이동");
		return  "manager/pay/settlementRequestList";
	}
	
	@GetMapping("/settlementHistoryList")
	public String managerSettlementHistoryList() {
		System.out.println("관리자 정산 내역 페이지 이동");
		return  "manager/pay/settlementHistoryList";
	}

}
