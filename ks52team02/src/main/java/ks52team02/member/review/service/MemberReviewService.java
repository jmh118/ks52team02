package ks52team02.member.review.service;

import java.util.List;

import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.dto.Pay;

public interface MemberReviewService {
	
	// 멘토의 평점 평균 조회
	double getMentorReviewAvg(String memberId);
	
	// 멘토별 후기 조회 시 해당 멘토 이메일 조회 
	String getMentorEmailById(String memberId);
	
	// 멘토별 후기 조회
	List<Review> getReviewListByMentor(String memberId);
	
	// 후기 등록
	void addReview(Review review);
	
	// 수정을 위한 특정 후기 조회
	Review getReviewByReviewCode(String reviewCode, String memberId);
	
	// 작성한 후기 목록 조회
	List<Review> getReviewListById(String memberId);
	
	// 등록한 후기가 있는지 조회
	List<Boolean> isCheckReview(List<Pay> paymentList);
	
	// 후기 내역 개수로 true, false 판단
	boolean isReviewCntPayCode(String payCode);


}
