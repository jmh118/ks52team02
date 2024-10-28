package ks52team02.manager.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.review.mapper.ManagerReviewMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerReviewServiceImpl implements ManagerReviewService {
	
	private final ManagerReviewMapper managerReviewMapper;

	@Override
	public void removeReview(String reviewCode) {
		
		managerReviewMapper.removeReview(reviewCode);
		
	}

}
