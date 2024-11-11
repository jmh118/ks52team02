package ks52team02.member.review.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.common.util.DateFormatterUtil;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.service.ManagerMemberService;
import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.dto.Pay;
import ks52team02.member.review.mapper.MemberReviewMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberReviewServiceImpl implements MemberReviewService {

	private final MemberReviewMapper memberReviewMapper;
	private final DateFormatterUtil dateFormatterUtil;
	private final CommonMapper commonMapper;
	private final ManagerMemberService managerMemberService;
	
	
	@Override
	public double getMentorReviewAvg(String memberId) {
		
		double reviewAvg = memberReviewMapper.getMentorReviewAvg(memberId);
		
		return reviewAvg;
	}
	
	
	@Override
	public String getMentorEmailById(String memberId) {
		
		Member memberInfo = managerMemberService.getMemberInfoById(memberId);
		String memberEmail = memberInfo.getMemberEmail();
		
		return memberEmail;
	}
	
	
	@Override
	public List<Review> getReviewListByMentor(String memberId) {
		
		List<Review> reviewList = memberReviewMapper.getReviewListByMentor(memberId);
		
		for (Review review : reviewList) {
	        String formattedDate = dateFormatterUtil.formatDate(review.getNoticeDetail().getMentoringYmd());
	        String formattedTime = dateFormatterUtil.formatTime(review.getNoticeDetail().getMentoringTime());

	        review.getNoticeDetail().setMentoringYmd(formattedDate);
	        review.getNoticeDetail().setMentoringTime(formattedTime);
	    }
		
		return reviewList;
	}
	
	
	@Override
	public void addReview(Review review) {
		
		String newReviewCode = commonMapper.getPrimaryKey("mentoring_postscript", "mentoring_postscript_code", "mentoring_postscript_");
		review.setReviewCode(newReviewCode);
		
		memberReviewMapper.addReview(review);
	}
	
	@Override
	public Review getReviewByReviewCode(String reviewCode, String memberId) {
		
		Review review = memberReviewMapper.getReviewByReviewCode(reviewCode, memberId);
		
		return review;
	}
	
	@Override
	public PageInfo<Review> getReviewListById(String memberId, Pageable pageable) {
		
		int rowCnt = memberReviewMapper.getReviewListCntById(memberId);
		pageable.setRowPerPage(5);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rowPerPage", pageable.getRowPerPage());
		params.put("offset", pageable.getOffset());
		params.put("memberId", memberId);
		
		List<Review> contents = memberReviewMapper.getReviewListById(params);
		  
		  for (Review review : contents) {
		        String formattedDate = dateFormatterUtil.formatDate(review.getNoticeDetail().getMentoringYmd());
		        String formattedTime = dateFormatterUtil.formatTime(review.getNoticeDetail().getMentoringTime());

		        review.getNoticeDetail().setMentoringYmd(formattedDate);
		        review.getNoticeDetail().setMentoringTime(formattedTime);
		    }	
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public List<Boolean> isCheckReview(PageInfo<Pay> paymentList) {
		List<Boolean> isCheck = new ArrayList<>();
		
		for (Pay pay : paymentList.getContents()) {
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
