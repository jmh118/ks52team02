package ks52team02.member.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mentoring.mapper.MentoringMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentoringServiceImpl implements MentoringService{
	
	private final MentoringMapper mentoringMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public void modifyQuestion(NoticeQuestion noticeQuestion) {
		mentoringMapper.modifyQuestion(noticeQuestion);
		
	}
	
	@Override
	public boolean getApplyCheck(Member member) {
		boolean isDuplicate = false;
		isDuplicate = mentoringMapper.getApplyCheck(member);
		return isDuplicate;
	}
	
	
	@Override
	public void modifyNotice(Notice notice) {
		mentoringMapper.modifyNotice(notice);
		
	}
	
	@Override
	public Notice getNoticeInfoByCode(String noticeCode) {
		Notice noticeInfo = mentoringMapper.getNoticeInfoByCode(noticeCode);
		
		return noticeInfo;
	}
	
	@Override
	public void addMentoringApply(MentoringApply mentoringApply) {
		String nextCode = commonMapper.getPrimaryKey("mentoring_apply", "mentoring_apply_code", "mentoring_apply_code_");
		mentoringApply.setApplyCode(nextCode);
		
		mentoringMapper.addMentoringApply(mentoringApply);
		String noticeDetailCode = mentoringApply.getNoticeDetailCode();
		mentoringMapper.modifyNoticeDetailTime(noticeDetailCode);
	}
	
	@Override
	public void addNoticeAnswer(NoticeAnswer noticeAnswer) {
		String nextCode = commonMapper.getPrimaryKey("mentoring_notice_question_answer","mentoring_notice_question_answer_code","mentoring_notice_question_answer_code_");
		noticeAnswer.setAnswerCode(nextCode);
		mentoringMapper.addNoticeAnswer(noticeAnswer);
		
	}
	
	@Override
	public void addNoticeQuestion(NoticeQuestion noticeQuestion) {
		//코드추가
		String nextCode = commonMapper.getPrimaryKey("mentoring_notice_question", "mentoring_notice_question_code", "mentoring_notice_question_code_");
		noticeQuestion.setQuestionCode(nextCode);
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
		String nextCode = commonMapper.getPrimaryKey("mentoring_notice", "mentoring_notice_code", "mentoring_notice_code_");
		notice.setNoticeCode(nextCode);
		
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
