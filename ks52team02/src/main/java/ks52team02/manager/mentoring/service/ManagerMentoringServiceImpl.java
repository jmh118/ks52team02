package ks52team02.manager.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.mentoring.dto.ManagerMetoringNotice;
import ks52team02.manager.mentoring.mapper.ManagerMentoringMapper;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerMentoringServiceImpl implements ManagerMentoringService{
	
	private final ManagerMentoringMapper managerMentoringMapper;
	
	@Override
	public PageInfo<MentoringApply> getManagerMentoringApplyList(Pageable pageable) {
		
		int rowCnt = managerMentoringMapper.getManagerMentoringApplyCount();
		List<MentoringApply> contents = managerMentoringMapper.getManagerMentoringApplyList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public void removeQuestion(String questionCode) {
		String answerCode = managerMentoringMapper.getManagerAnswerCodeByCode(questionCode);
		managerMentoringMapper.removeAnswer(answerCode);
		managerMentoringMapper.removeQuestion(questionCode);
	}
	
	@Override
	public void removeAnswer(String answerCode) {
		
		managerMentoringMapper.removeAnswer(answerCode);
	}
	
	@Override
	public PageInfo<ManagerMetoringNotice> getManagerNoticeList(Pageable pageable) {
		
		int rowCnt = managerMentoringMapper.getNoticeListCount();
		
		List<ManagerMetoringNotice> contents = managerMentoringMapper.getManagerNoticeList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	
	@Override
	public PageInfo<NoticeQuestion> getManagerNoticeQuestionList(Pageable pageable) {

		int rowCnt = managerMentoringMapper.getManagerNoticeQuestionCount();
		
		List<NoticeQuestion> contents = managerMentoringMapper.getManagerNoticeQuestionList(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<NoticeAnswer> getManagerNoticeAnswerList(Pageable pageable) {
		int rowCnt = managerMentoringMapper.getManagerNoticeAnswerCount();
		List<NoticeAnswer> contents = managerMentoringMapper.getManagerNoticeAnswerList(pageable);
		return new PageInfo<>(contents, pageable, rowCnt);
	}
}
