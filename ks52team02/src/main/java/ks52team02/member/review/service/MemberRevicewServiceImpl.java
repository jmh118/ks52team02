package ks52team02.member.review.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.review.dto.MemberReview;
import ks52team02.member.review.mapper.MemberReviewMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberRevicewServiceImpl implements MemberReviewService {

	private final MemberReviewMapper memberReviewMapper;
	
	@Override
	public int modifyReview(Map<String,Object> reviewMap) {
		
		int result = memberReviewMapper.modifyReview(reviewMap);
		System.out.println("결과" + result);
		return result;
	}

	
	@Override
	public MemberReview getReviewById(String payCode, String memberId) {
		
		MemberReview review = memberReviewMapper.getReviewById(payCode, memberId);
		
		return review;
	}
	
	
	@Override
	public boolean isReviewCntPayCode(String payCode) {
		
		boolean isReview = false;
		int reviewCnt = memberReviewMapper.getReviewCntByPayCode(payCode);
		
		if(reviewCnt > 0) isReview = true;
		
		return isReview;
	}
}
