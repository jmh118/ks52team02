package ks52team02.member.review.service;

import java.util.Map;

import ks52team02.member.review.dto.MemberReview;

public interface MemberReviewService {
	
	// 작성한 후기 수정
	int modifyReview(Map<String,Object> reviewMap);
	
	// 수정 위해서 후기 내용 조회
	MemberReview getReviewById(String payCode, String memberId);
	
	// 결제 코브로 후기 작성했는지 조회
	boolean isReviewCntPayCode(String payCode);

}
