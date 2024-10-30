package ks52team02.member.review.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.review.dto.Review;


@Mapper
public interface MemberReviewMapper {
	
	// 등록한 후기 조회
	List<Review> getReviewListById(String memberId);
	
	// 등록한 후기가 있는지 조회
	int getReviewCntByPayCode(String payCode);
	
}
