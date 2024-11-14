package ks52team02.manager.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.review.dto.Review;
import ks52team02.manager.review.mapper.ManagerReviewMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerReviewServiceImpl implements ManagerReviewService {
	
	private final ManagerReviewMapper managerReviewMapper;
	
	@Override
	public PageInfo<Review> getAllMentoringReviewList(Pageable pageable) {
		
		int rowCnt = managerReviewMapper.getReviewListCount();
		
		List<Review> contents = managerReviewMapper.getAllMentoringReviewList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public int removeReview(String reviewCode) {
		
		return managerReviewMapper.removeReview(reviewCode);
		
	}

}
