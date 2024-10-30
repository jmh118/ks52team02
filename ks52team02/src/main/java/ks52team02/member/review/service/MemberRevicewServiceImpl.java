package ks52team02.member.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.review.mapper.MemberReviewMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberRevicewServiceImpl implements MemberReviewService {

	private final MemberReviewMapper memberReviewMapper;
	
	
	@Override
	public int modifyReview(Map<String,Object> reviewMap) {
		
		
		return 0;
	}

	
	@Override
	public Review getReviewById(String payCode, String memberId) {
		
		Review review = memberReviewMapper.getReviewById(payCode, memberId);
		
		return review;
	}
	
	@Override
	public List<Boolean> isCheckReview(List<Pay> paymentList) {
		List<Boolean> isCheck = new ArrayList<>();
		
		for (Pay pay : paymentList) {
            String payCode = pay.getPaySettlementCalCode();
            boolean isReviewExist = isReviewCntPayCode(payCode);
            isCheck.add(isReviewExist);
        }
		
		return isCheck;
	}
	
	
	
	@Override
	public boolean isReviewCntPayCode(String payCode) {
		
		boolean isReview = false;
		int reviewCnt = memberReviewMapper.getReviewCntByPayCode(payCode);
		
		if(reviewCnt > 0) isReview = true;
		
		return isReview;
	}
}
