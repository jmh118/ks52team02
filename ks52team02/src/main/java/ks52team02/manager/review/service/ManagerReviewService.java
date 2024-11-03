package ks52team02.manager.review.service;

import ks52team02.manager.review.dto.Review;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface ManagerReviewService {
	
	// 멘토링 후기 삭제
	void removeReview(String reviewCode);
	
	// 전체 후기 조회
	PageInfo<Review> getAllMentoringReviewList(Pageable pageable);

}
