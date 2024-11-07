package ks52team02.manager.mentoring.service;

import java.util.List;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;



public interface ManagerMentoringService {
	
	// 멘토링공고 조회
	PageInfo<ManagerMetoringNotice> getManagerNoticeList(Pageable pageable, String searchId);

	// 멘토링공고 질문 조회
	PageInfo<NoticeQuestion> getManagerNoticeQuestionList(Pageable pageable);

	// 멘토링공고 답변 조회
	PageInfo<NoticeAnswer> getManagerNoticeAnswerList(Pageable pageable);

	// 멘토링공고 답변 삭제
	void removeAnswer(String answerCode);

	// 멘토링공고 질문 삭제(답변있을시 답변도삭제)
	void removeQuestion(String questionCode);
	
	// 멘토링 신청 조회
	PageInfo<MentoringApply> getManagerMentoringApplyList(Pageable pageable, String searchId);

}
