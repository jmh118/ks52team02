package ks52team02.manager.mentoring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.page.Pageable;

@Mapper
public interface ManagerMentoringMapper {
	
	// 멘토링공고 조회
	List<ManagerMetoringNotice> getManagerNoticeList(Map<String, Object> paramMap);

	// 멘토링 공고 갯수 조회
	int getNoticeListCount(String searchId);

	// 공고 질문 수 조회
	int getManagerNoticeQuestionCount();

	// 멘토링공고 질문 조회
	List<NoticeQuestion> getManagerNoticeQuestionList(Pageable pageable);

	// 멘토링 공고 답변 수 조회
	int getManagerNoticeAnswerCount();

	// 멘토링공고 답변 조회
	List<NoticeAnswer> getManagerNoticeAnswerList(Pageable pageable);

	// 멘토링공고 답변 삭제
	int removeAnswer(String answerCode);

	// 질문에 해당하는 답변 코드 조회
	String getManagerAnswerCodeByCode(String questionCode);

	// 멘토링공고 질문 삭제
	int removeQuestion(String questionCode);

	// 멘토링 신청 수 조회
	int getManagerMentoringApplyCount(String searchId);

	// 멘토링 신청 조회
	List<MentoringApply> getManagerMentoringApplyList(Map<String, Object> paramMap);

	

}
