package ks52team02.member.review.service;

import java.util.List;
import java.util.Map;

import ks52team02.manager.review.dto.Review;
import ks52team02.member.pay.dto.Pay;

public interface MemberReviewService {
	
	// 작성한 후기 수정
	int modifyReview(Map<String,Object> reviewMap);
	
	// 수정 위해서 후기 내용 조회
	Review getReviewById(String payCode, String memberId);
	
	// 결제코드를 이용해서 등록한 후기가 있는지 조회
	List<Boolean> isCheckReview(List<Pay> paymentList);
	
	// 결제 코드로 후기 작성했는지 조회
	boolean isReviewCntPayCode(String payCode);

}
