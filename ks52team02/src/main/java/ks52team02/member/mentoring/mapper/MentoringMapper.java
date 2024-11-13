package ks52team02.member.mentoring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.page.Pageable;

@Mapper
public interface MentoringMapper {
	
	// 공고관련답변 등록
	int addNoticeAnswer(NoticeAnswer noticeAnswer);
	
	// 공고관련질문 등록
	int addNoticeQuestion(NoticeQuestion NoticeQuestion);
	
	// 공고관련질문답변 조회
	List<NoticeQuestion> getNoticeQuestionByCode(String noticeCode);
	
	// 신청가능한 요일 시간 조회
	List<NoticeDetail> getNoticeApplyYmdByCode(String noticeCode);
	
	// 공고등록
	int addNotice(Notice notice);
	
	// 공고조회
	List<Notice> getNoticeList(Map<String, Object> paramMap);
	
	// 공고카테고리조회
	List<Topic> getTopicList();

	// 공고상세조회
	Notice getNoticeDetailByCode(String noticeCode);

	// 공고 상세 멘토링 시간
	List<NoticeDetail> getNoticeDetailTimeByCode(String noticeCode);

	// 멘토링 신청
	int addMentoringApply(MentoringApply mentoringApply);

	// 특정공고 조회
	Notice getNoticeInfoByCode(String noticeCode);

	// 공고 수정
	int modifyNotice(Notice notice);

	//회원정보확인
	Member checkApply(String searchId);

	// 멘토링 신청 후 신청가능한 멘토링 시간 수정
	int modifyNoticeDetailTime(String noticeDetailCode);

	// 공고질문 수정
	int modifyQuestion(NoticeQuestion noticeQuestion);

	// 신청한 멘티 프로필조회
	List<MenteeProfile> getApplyMenteeProfileById(String memberId);

	// 카테고리 조회
	List<Topic> getCategoryCountList();

	// 공고상세등록을위한 마지막 공고코드조회
	String getLastNoticeCode();

	// 공고상세등록
	int addNoticeDetail(NoticeDetail detail);

	// 공고 질문에대한답변 수정
	int modifyAnswer(NoticeAnswer noticeAnswer);

	// 공고 개수 조회
	int getNoticeListCount(String category);

	// 메인화면 공고 조회
	List<Notice> getNoticeMainList();

	


}
