package ks52team02.member.pay.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.common.util.DateFormatterUtil;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.mapper.MemberPayMapper;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberPayServiceImpl implements MemberPayService {

	private final DateFormatterUtil dateFormatterUtil;
	private final MemberPayMapper memberPayMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public boolean addSettlementApply(String payCode, String noticeCode, String memberId) {
		
		boolean isApply = false;
		String key = commonMapper.getPrimaryKey("mentor_calculation_demand_completion", "mentor_calculation_demand_completion_code", "mentor_calculation_demand_completion_code_");
		
		Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("noticeCode", noticeCode);
        params.put("payCode", payCode);
        params.put("memberId", memberId);
		
		
		int result = memberPayMapper.addSettlementApply(params);
		
		if(result > 0) isApply = true;
		
		return isApply;
	}
	
	@Override
	public List<Boolean> isCheckSettlement(List<Pay> paymentList) {
		
		List<Boolean> isCheck = new ArrayList<>();

		for (Pay pay : paymentList) {
            String payCode = pay.getPaySettlementCalCode();
            boolean isSettlementExist = isSettlementCntByPayCode(payCode);
            isCheck.add(isSettlementExist);
        }

		return isCheck;
	}
	
	
	@Override
	public boolean isSettlementCntByPayCode(String payCode) {
		
		boolean isSettlement = false;
		int settlementCnt = memberPayMapper.getSettlementCntByPayCode(payCode);
		
		if(settlementCnt > 0) isSettlement = true; 
		
		return isSettlement;
	}
	
	@Override
	public List<Pay> getPaymentListByMentorId(String memberId) {
		
		List<Pay> paymentList = memberPayMapper.getPaymentListByMentorId(memberId);
		
		for (Pay pay : paymentList) {
	        String formattedDate = dateFormatterUtil.formatDate(pay.getNoticeDetail().getMentoringYmd());
	        String formattedTime = dateFormatterUtil.formatTime(pay.getNoticeDetail().getMentoringTime());

	        pay.getNoticeDetail().setMentoringYmd(formattedDate);
	        pay.getNoticeDetail().setMentoringTime(formattedTime);
	    }	
		
		return paymentList;
	}
	
	@Override
	public String getMentoringTitleByPayCode(String payCode) {
		
		String titleName = memberPayMapper.getMentoringTitleByPayCode(payCode);
				
		return titleName;
	}
	
	@Override
	public List<Pay> getMenteePaymentListById(String memberId) {
		
		List<Pay> paymentList = memberPayMapper.getMenteePaymentListById(memberId);
		
		for (Pay pay : paymentList) {
	        String formattedDate = dateFormatterUtil.formatDate(pay.getNoticeDetail().getMentoringYmd());
	        String formattedTime = dateFormatterUtil.formatTime(pay.getNoticeDetail().getMentoringTime());

	        pay.getNoticeDetail().setMentoringYmd(formattedDate);
	        pay.getNoticeDetail().setMentoringTime(formattedTime);
	    }	
		
		return paymentList;
	}
}
