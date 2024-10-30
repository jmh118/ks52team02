package ks52team02.member.review.service;

import java.util.List;

import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.dto.Pay;

public interface MemberReviewService {
	
	
	// 작성한 후기 목록 조회
	List<Review> getReviewListById(String memberId);
	
	// 결제코드를 이용해서 등록한 후기가 있는지 조회한 결과를 이용해서 결제 조회 화면에 List로 전달
	List<Boolean> isCheckReview(List<Pay> paymentList);
	
	// 결제코드로 후기 작성했는지 조회
	boolean isReviewCntPayCode(String payCode);

}
