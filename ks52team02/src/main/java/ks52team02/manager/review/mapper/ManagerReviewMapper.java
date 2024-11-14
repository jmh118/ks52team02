package ks52team02.manager.review.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.review.dto.MentorReviewData;
import ks52team02.manager.review.dto.Review;
import ks52team02.page.Pageable;

@Mapper
public interface ManagerReviewMapper {
	
	// 관리자가 후기 삭제 
	int removeReview(String reviewCode);
	
	// 멘토별 후기 통계 행 개수 조회
	int getMentorReviewsDataListCnt();
	
	// 멘토별 후기 관련해서 통계 조회
	List<MentorReviewData> getMentorReviewsDataList(Pageable pageable);
	
	// 후기 내역 행 개수 조회
	int getReviewListCount();

	// 전체 후기 조회
	List<Review> getAllMentoringReviewList(Pageable pageable);
}
