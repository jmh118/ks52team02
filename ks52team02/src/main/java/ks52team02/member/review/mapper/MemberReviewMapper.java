package ks52team02.member.review.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.review.dto.Review;

@Mapper
public interface MemberReviewMapper {
	
	// 작성한 후기 수정
	int modifyReview(Map<String,Object> reviewMap);
	
	// 수정을 위한 후기 조회
	Review getReviewById(String payCode, String memberId);
	
	// 등록한 후기가 있는지???
	int getReviewCntByPayCode(String payCode);
	
}
