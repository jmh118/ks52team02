package ks52team02.member.review.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.review.dto.Review;


@Mapper
public interface MemberReviewMapper {
	
	// 멘토별 후기 조회
	List<Review> getReviewListByMentor(String memberId);
	
	// 후기 등록
	int addReview(Review review);
	
	// 후기 수정
	int modifyReview(Review review);
	
	// 특정 후기 조회 (후기 수정을 위한...)
	Review getReviewByReviewCode(String reviewCode, String memberId);
	
	// 등록한 후기 조회
	List<Review> getReviewListById(String memberId);
	
	// 등록한 후기가 있는지 조회
	int getReviewCntByPayCode(String payCode);
	
}
