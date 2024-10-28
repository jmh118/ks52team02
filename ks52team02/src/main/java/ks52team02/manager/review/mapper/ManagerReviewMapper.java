package ks52team02.manager.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.review.dto.MentorReviews;
import ks52team02.manager.review.dto.Review;

@Mapper
public interface ManagerReviewMapper {
	
	// 관리자가 후기 삭제 
	int removeReview(String reviewCode);
	
	// 멘토별 후기 관련해서 통계 조회
	List<MentorReviews> getMentorReviewsDataList();

	// 전체 후기 조회
	List<Review> getAllMentoringReviewList();
}
