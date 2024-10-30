package ks52team02.member.review.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.util.DateFormatterUtil;
import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.review.mapper.MemberReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberReviewServiceImpl implements MemberReviewService {

	private final MemberReviewMapper memberReviewMapper;
	private final DateFormatterUtil dateFormatterUtil;
	
	@Override
	public Review getReviewByReviewCode(String reviewCode, String memberId) {
		
		Review review = memberReviewMapper.getReviewByReviewCode(reviewCode, memberId);
		
		return review;
	}
	
	@Override
	public List<Review> getReviewListById(String memberId) {
		
		List<Review> reviewList = memberReviewMapper.getReviewListById(memberId);
		  
		  for (Review review : reviewList) {
		        String formattedDate = dateFormatterUtil.formatDate(review.getNoticeDetail().getMentoringYmd());
		        String formattedTime = dateFormatterUtil.formatTime(review.getNoticeDetail().getMentoringTime());

		        review.getNoticeDetail().setMentoringYmd(formattedDate);
		        review.getNoticeDetail().setMentoringTime(formattedTime);
		    }	
		
		return reviewList;
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
