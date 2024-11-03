package ks52team02.manager.honor.service;

import java.util.List;

import ks52team02.manager.member.dto.Member;
import ks52team02.manager.review.dto.MentorReviewData;

public interface ManagerHonorService {
	
	// 명예멘토 취소 
	int honorMentorCancel(String memeberId);
	
	// 명예멘토 승인
	int honorMentorApprove(String memeberId);
	
	
	// 후기 개수, 평점 멘토 목록 조회
	List<MentorReviewData> getMentorReviewsDataList();
	
	// 명예멘토 리스트 조회
	List<Member> getHornorMentorList();

}
