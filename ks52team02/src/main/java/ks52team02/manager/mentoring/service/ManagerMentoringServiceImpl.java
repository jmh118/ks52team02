package ks52team02.manager.mentoring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public PageInfo<MentoringApply> getManagerMentoringApplyList(Pageable pageable, String searchId) {
		
		int rowCnt = managerMentoringMapper.getManagerMentoringApplyCount(searchId);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("searchId",searchId);
		
		List<MentoringApply> contents = managerMentoringMapper.getManagerMentoringApplyList(paramMap);
		
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
	public PageInfo<ManagerMetoringNotice> getManagerNoticeList(Pageable pageable, String searchId) {
		
		int rowCnt = managerMentoringMapper.getNoticeListCount(searchId);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("searchId",searchId);
		
		List<ManagerMetoringNotice> contents = managerMentoringMapper.getManagerNoticeList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	
	@Override
	public PageInfo<NoticeQuestion> getManagerNoticeQuestionList(Pageable pageable, String searchId) {

		int rowCnt = managerMentoringMapper.getManagerNoticeQuestionCount(searchId);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("searchId",searchId);
		
		List<NoticeQuestion> contents = managerMentoringMapper.getManagerNoticeQuestionList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<NoticeAnswer> getManagerNoticeAnswerList(Pageable pageable, String searchId) {
		int rowCnt = managerMentoringMapper.getManagerNoticeAnswerCount(searchId);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("searchId",searchId);
		
		List<NoticeAnswer> contents = managerMentoringMapper.getManagerNoticeAnswerList(paramMap);
		return new PageInfo<>(contents, pageable, rowCnt);
	}
}
