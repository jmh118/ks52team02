package ks52team02.member.pay.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.common.util.DateFormatterUtil;
import ks52team02.member.pay.dto.BeforePay;
import ks52team02.member.pay.dto.MentoringData;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.dto.SearchFilter;
import ks52team02.member.pay.mapper.MemberPayMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberPayServiceImpl implements MemberPayService {

	private final DateFormatterUtil dateFormatterUtil;
	private final MemberPayMapper memberPayMapper;
	private final CommonMapper commonMapper;
	
	
	@Override
	public List<Pay> getFilterMenteePaymentListById(String memberId, SearchFilter searchFilter) {
		
		Map<String, Object> filterParams = new HashMap<>();
		
		filterParams.put("memberId", memberId);
		
		if (searchFilter != null) {
	        filterParams.put("selectedYear", searchFilter.getSelectedYear());
	        filterParams.put("selectedMonth", searchFilter.getSelectedMonth());
	    }
		
		List<Pay> payList = memberPayMapper.getFilterMenteePaymentListById(filterParams);
		
		for (Pay pay : payList) {
	        String formattedDate = dateFormatterUtil.formatDate(pay.getNoticeDetail().getMentoringYmd());
	        String formattedTime = dateFormatterUtil.formatTime(pay.getNoticeDetail().getMentoringTime());

	        pay.getNoticeDetail().setMentoringYmd(formattedDate);
	        pay.getNoticeDetail().setMentoringTime(formattedTime);
	    }	
		
		return payList;
	}
	
	
	@Override
	public void removeMentoringApplyByCode(String applyCode, String detailCode) {
		
		int removeResult = memberPayMapper.removeMentoringApplyByCode(applyCode);
		if (removeResult > 0) {
			memberPayMapper.updateMentoringDetailStatusByCode(detailCode);
		}
		
	}
	
	@Override
	public int addPay(List<MentoringData> mentoringDataList) {
		
		List<String> applyCodeList = new ArrayList<>();
		
		int result = 0;
		log.info("impl mentoringData: {}", mentoringDataList);
		for (MentoringData mentoringData : mentoringDataList) {
			String newReviewCode = commonMapper.getPrimaryKey("mentee_settlement_mentor_calculation", "mentee_settlement_mentor_calculation_code", "mentee_settlement_mentor_calculation_code_");
            mentoringData.setPayCode(newReviewCode);
            applyCodeList.add(mentoringData.getApplyCode());
			result = memberPayMapper.addPay(mentoringData);
			result += result;
			
		}
		if(result == 0) return result; 
		
		int upre = memberPayMapper.updateMentoringApplyStarus(applyCodeList);
		log.info("신청 상태 업데이트 결과 : {}", upre);
		
		List<String> detailCodeList = memberPayMapper.getNoticeDetailCodeByApplyCode(applyCodeList);
		int updre = memberPayMapper.updateMentroingDatailStatus(detailCodeList);
		log.info("공고 상세 코드 목록 : {}", detailCodeList);
		log.info("공고 상세 업데이트 결과 : {}", updre);
		
		
		return result;
	}
	
	@Override
	public int getBeforePayCnt(String memberId) {
		
		int beforePayCnt = memberPayMapper.getBeforePayCnt(memberId);
		
		return beforePayCnt;
	}
	
	
	@Override
	public List<BeforePay> getBeforePayListById(String memberId) {
		
		List<BeforePay> beforePayList = memberPayMapper.getBeforePayListById(memberId);		
		
		for (BeforePay beforePay : beforePayList) {
	        String formattedDate = dateFormatterUtil.formatDate(beforePay.getNoticeDetail().getMentoringYmd());
	        String formattedTime = dateFormatterUtil.formatTime(beforePay.getNoticeDetail().getMentoringTime());

	        beforePay.getNoticeDetail().setMentoringYmd(formattedDate);
	        beforePay.getNoticeDetail().setMentoringTime(formattedTime);
	    }	
		
		return beforePayList;
	}
	
	
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
