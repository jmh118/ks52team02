package ks52team02.member.pay.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.servlet.http.HttpSession;
import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.member.pay.dto.BeforePay;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.dto.PaymentRequest;
import ks52team02.member.pay.dto.SearchFilter;
import ks52team02.member.pay.mapper.MemberPayMapper;
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
	private final IamportClient iamportClient = new IamportClient("2328516881331875", 
								"l3OOEUYVupHQ01RXV85v2JjWjy1t0XBbqRuZ3tptIixCZKrXJ1JhLWhxoXAkn0PD1j9vRm0oy8fGpILt");
	
	@PostMapping("/searchSettlementHistoryList")
	@ResponseBody
	public List<PaymentSettlement> searchSettlementHistoryList(SearchFilter searchFilter, HttpSession session){
		
		String memberId = (String) session.getAttribute("SID");
		List<PaymentSettlement> settlementList = memberPayService.searchSettlementHistoryList(memberId, searchFilter);
		
		log.info("32-42-0=340-2=3 : {}", settlementList);
		
		return settlementList;
	}
	
	
	@GetMapping("/settlementHistoryList")
	public String getSettlementHistoryListById(Model model, HttpSession session) {
		
		String memberId = (String) session.getAttribute("SID");
		List<PaymentSettlement> settlementList = memberPayService.getSettlementHistoryList(memberId);
		
		model.addAttribute("activeMenu", "settlementHistoryList");
		model.addAttribute("settlementList", settlementList);
		
		return "member/pay/settlementHistoryList";
	}
	
	@PostMapping("/searchList")
	@ResponseBody
	public List<Pay> searchPayList(SearchFilter searchFilter, HttpSession session){
		
		String memberId = (String) session.getAttribute("SID");
		String memberLevel = (String) session.getAttribute("SLEVEL");
		
		List<Pay> payList = memberPayService.getFilterMemberPaymentListById(memberId, memberLevel, searchFilter);
		
		
		return payList;
	}
	
	@GetMapping("remove")
	public String removeApply(@RequestParam(name="applyCode") String applyCode, @RequestParam(name="detailCode") String detailCode) {
		
		memberPayService.removeMentoringApplyByCode(applyCode, detailCode);
		
		return "redirect:/pay/beforeList";
	}
	
	@GetMapping("/beforeList")
	public String getBeforePayList(Model model, HttpSession session) {
		
		String memberId = (String) session.getAttribute("SID");
		List<BeforePay> beforePayList = memberPayService.getBeforePayListById(memberId);
		
		model.addAttribute("beforePayList", beforePayList);
		
		return "member/pay/beforePayList";
	}
	
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
	
	@PostMapping("/process")
	public ResponseEntity<String> handlePaymentSuccess(@RequestBody PaymentRequest paymentRequest, HttpSession session) {
		
		log.info("Received impUid: {}", paymentRequest.getImpUid());
	    log.info("Received totalAmount: {}", paymentRequest.getTotalAmount());
	    log.info("Received mentoringData: {}", paymentRequest.getMentoringData());
		
	    if (paymentRequest.getImpUid() == null || paymentRequest.getImpUid().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("imp_uid가 누락되었습니다.");
	    }

	    try {
	        IamportResponse<Payment> paymentResponse = iamportClient.paymentByImpUid(paymentRequest.getImpUid());

	        if (paymentResponse != null && paymentResponse.getResponse() != null) {
	            Payment paymentData = paymentResponse.getResponse();
	            if (paymentData.getAmount().intValue() == paymentRequest.getTotalAmount()) {
	                int result = memberPayService.addPay(paymentRequest.getMentoringData());
	                log.info("디비 등록 결과 : {}", result);
	                return ResponseEntity.ok("결제 완료 및 데이터 저장 완료");
	            } else {
	                log.error("결제 금액 불일치: 요청 금액 {} != 실제 결제 금액 {}", paymentRequest.getTotalAmount(), paymentData.getAmount());
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 금액 불일치");
	            }
	        } else {
	            log.error("유효하지 않은 결제 데이터");
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 결제 데이터");
	        }

	    } catch (Exception e) {
	        log.error("결제 검증 실패", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 검증 실패");
	    }
	}
	
	@GetMapping("/success")
	public String paymentStatusIsSuccessView() {
		
		
		return "member/pay/payStatusSuccess";
	}
	
	

}
