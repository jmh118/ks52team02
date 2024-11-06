package ks52team02.member.mentoring.service;

import java.util.List;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface MentoringService {
	
	// 공고관련질문에 대한 답변 등록
	void addNoticeAnswer(NoticeAnswer noticeAnswer);
	
	// 공고관련질문 등록
	void addNoticeQuestion(NoticeQuestion noticeQuestion);
	
	//공고등록
	void addNotice(Notice notice);
	
	// 공고목록조회
	PageInfo<Notice> getNoticeList(String category, Pageable pageable);
	
	// 공고카테고리조회
	List<Topic> getTopicList();

	// 공고 상세 멘토링 시간
	List<NoticeDetail> getNoticeDetailTimeByCode(String noticeCode);

	// 공고관련질문답변 조회
	List<NoticeQuestion> getNoticeQuestionByCode(String noticeCode);

	// 멘토링 신청
	void addMentoringApply(MentoringApply mentoringApply);

	// 특정공고 조회
	Notice getNoticeInfoByCode(String noticeCode);

	// 공고수정
	void modifyNotice(Notice notice);

	// 신청확인
	boolean getApplyCheck(Member member);

	// 공고질문 수정
	void modifyQuestion(NoticeQuestion noticeQuestion);
	
	// 멘티프로필 조회
	List<MenteeProfile> getApplyMenteeProfileById(String memberId);

	//카테고리 개수
	List<Topic> getCategoryCountList();

	// 공고상세 추가
	void addNoticeDetail(NoticeDetail noticeDetail);

	// 공고답변 수정
	void modifyAnswer(NoticeAnswer noticeAnswer);

	// 메인화면 공고 조회
	List<Notice> getNoticeMainList();

	// 공고답변 삭제
	void removeNoticeAnswer(String answerCode);

	// 공고질문 삭제
	void removeNoticeQuestion(String questionCode);



}
