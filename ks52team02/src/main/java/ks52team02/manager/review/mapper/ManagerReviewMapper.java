package ks52team02.manager.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.review.dto.Review;

@Mapper
public interface ManagerReviewMapper {

	// 전체 후기 조회
	List<Review> getAllMentoringReviewList();
}
