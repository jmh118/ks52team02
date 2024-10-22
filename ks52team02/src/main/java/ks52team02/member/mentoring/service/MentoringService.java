package ks52team02.member.mentoring.service;

import java.util.List;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.Topic;

public interface MentoringService {
	// 공고 코드 추가
	String getNextNoticeCode();
	
	//공고등록
	void addNotice(Notice notice);
	
	// 공고목록조회
	List<Notice> getNoticeList();
	
	// 공고카테고리조회
	List<Topic> getTopicList();

	// 공고목록 카테고리별조회
	List<Notice> getNoticeByCategory(String category);

	// 공고 상세 멘토링 시간
	List<NoticeDetail> getNoticeDetailTimeByCode(String noticeCode);

}
