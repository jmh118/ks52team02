package ks52team02.member.mentoring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;

@Mapper
public interface MentoringMapper {
	
	// 공고관련답변 등록
	int addNoticeAnswer(NoticeAnswer noticeAnswer);
	
	// 공고관련답변 코드 추가
	String getNextNoticeAnswerCode();
	
	// 공고관련질문 등록
	int addNoticeQuestion(NoticeQuestion NoticeQuestion);
	
	// 공고관련질문 코드 추가
	String getNextNoticeQuestionCode();
	
	// 공고관련질문답변 조회
	List<NoticeQuestion> getNoticeQuestionByCode(String noticeCode);
	
	// 신청가능한 요일 조회
	List<NoticeDetail> getNoticeApplyYmdByCode(String noticeCode);
	
	// 공고 코드 추가
	String getNextNoticeCode();
	
	// 공고등록
	int addNotice(Notice notice);
	
	//공고조회
	List<Notice> getNoticeList();
	
	// 공고카테고리조회
	List<Topic> getTopicList();

	// 공고목록 카테고리별조회
	List<Notice> getNoticeByCategory(String category);

	// 공고상세조회
	Notice getNoticeDetailByCode(String noticeCode);

	// 공고 상세 멘토링 시간
	List<NoticeDetail> getNoticeDetailTimeByCode(String noticeCode);

	//멘토링 신청
	int addMentoringApply(MentoringApply mentoringApply);




}
