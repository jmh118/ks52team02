package ks52team02.member.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mentoring.mapper.MentoringMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MentoringServiceImpl implements MentoringService{
	
	private final MentoringMapper mentoringMapper;
	
	@Override
	public void addNoticeAnswer(NoticeAnswer noticeAnswer) {
		String nextCode = mentoringMapper.getNextNoticeAnswerCode();
		noticeAnswer.setAnswerCode("mentoring_notice_question_answer_code_" + nextCode);
		mentoringMapper.addNoticeAnswer(noticeAnswer);
		
	}
	
	@Override
	public void addNoticeQuestion(NoticeQuestion noticeQuestion) {
		//코드추가
		String nextCode = mentoringMapper.getNextNoticeQuestionCode();
		noticeQuestion.setQuestionCode("mentoring_notice_question_code_" + nextCode);
		mentoringMapper.addNoticeQuestion(noticeQuestion);
	}
	
	@Override
	public List<NoticeQuestion> getNoticeQuestionByCode(String noticeCode) {
		
		return mentoringMapper.getNoticeQuestionByCode(noticeCode);
	}
	
	@Override
	public List<NoticeDetail> getNoticeDetailTimeByCode(String noticeCode) {
		List<NoticeDetail> mentoringTime = mentoringMapper.getNoticeDetailTimeByCode(noticeCode);
		return mentoringTime;
	}

	
	@Override
	public List<Notice> getNoticeByCategory(String category) {

		List<Notice> noticeCateList = mentoringMapper.getNoticeByCategory(category);
		
		return noticeCateList;
	}

	
	@Override
	public void addNotice(Notice notice) {
		String nextCode = mentoringMapper.getNextNoticeCode();
		notice.setNoticeCode("mentoring_notice_code_" + nextCode);
		
		mentoringMapper.addNotice(notice);		
	}
	
	@Override
	public List<Topic> getTopicList() {
		
		return mentoringMapper.getTopicList();
	}
	
	@Override
		public List<Notice> getNoticeList() {
			
			List<Notice> noticeList = mentoringMapper.getNoticeList();
			
			return noticeList;
		}

}
