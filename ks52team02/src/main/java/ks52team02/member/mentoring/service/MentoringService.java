package ks52team02.member.mentoring.service;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;

public interface MentoringService {
	
	// 공고관련질문에 대한 답변 등록
	void addNoticeAnswer(NoticeAnswer noticeAnswer);
	
	// 공고관련질문 등록
	void addNoticeQuestion(NoticeQuestion noticeQuestion);
	
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

	// 공고관련질문답변 조회
	List<NoticeQuestion> getNoticeQuestionByCode(String noticeCode);



}
