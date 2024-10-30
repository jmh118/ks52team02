package ks52team02.member.pay.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.pay.dto.Pay;
import ks52team02.member.pay.mapper.MemberPayMapper;
import ks52team02.member.review.service.MemberReviewService;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberPayServiceImpl implements MemberPayService {

	private final MemberPayMapper memberPayMapper;
	
	@Override
	public String getMentoringTitleByPayCode(String payCode) {
		
		String titleName = memberPayMapper.getMentoringTitleByPayCode(payCode);
				
		return titleName;
	}
	
	
	@Override
	public List<Pay> getMenteePaymentListById(String memberId) {
		
		List<Pay> paymentList = memberPayMapper.getMenteePaymentListById(memberId);
		
		return paymentList;
	}
}
