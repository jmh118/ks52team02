package ks52team02.member.mentoring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.mentoring.mapper.ManagerMentoringMapper;
import ks52team02.member.mentoring.dto.MentoringApply;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeAnswer;
import ks52team02.member.mentoring.dto.NoticeDetail;
import ks52team02.member.mentoring.dto.NoticeQuestion;
import ks52team02.member.mentoring.dto.Topic;
import ks52team02.member.mentoring.mapper.MentoringMapper;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentoringServiceImpl implements MentoringService{
	
	private final MentoringMapper mentoringMapper;
	private final ManagerMentoringMapper managerMentoringMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public void removeNoticeQuestion(String questionCode) {
		managerMentoringMapper.removeQuestion(questionCode);
	}
	
	@Override
	public void removeNoticeAnswer(String answerCode) {
		managerMentoringMapper.removeAnswer(answerCode);
	}
	
	@Override
	public List<Notice> getNoticeMainList() {
		List<Notice> noticeList = mentoringMapper.getNoticeMainList();
		return noticeList;
	}
	
	@Override
	public void modifyAnswer(NoticeAnswer noticeAnswer) {
		mentoringMapper.modifyAnswer(noticeAnswer);	
	}
	
	@Override
	public List<Topic> getCategoryCountList() {
		List<Topic> categoryCount = mentoringMapper.getCategoryCountList();
		return categoryCount;
	}
	
	@Override
	public List<MenteeProfile> getApplyMenteeProfileById(String memberId) {
		List<MenteeProfile> menteeProfile = mentoringMapper.getApplyMenteeProfileById(memberId);
		return menteeProfile;
	}
	
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
	public void addNoticeDetail(NoticeDetail noticeDetail) {
		List<String> mentoringTimes = noticeDetail.getMentoringTimeList();
	    List<String> mentoringYmds = noticeDetail.getMentoringYmdList();
	    String lastNoticeCode = mentoringMapper.getLastNoticeCode();
	    for (int i = 0; i < mentoringTimes.size(); i++) {
	    	NoticeDetail detail = new NoticeDetail();
	    	String nextCode = commonMapper.getPrimaryKey("mentoring_notice_detail", "mentoring_notice_detail_code", "mentoring_notice_detail_code_");
	    	detail.setNoticeDetailCode(nextCode);
	    	detail.setNoticeCode(lastNoticeCode);
	    	detail.setMentoringYmd(mentoringYmds.get(i));
	    	detail.setMentoringTime(mentoringTimes.get(i));
	        
	        mentoringMapper.addNoticeDetail(detail);
	    }
		
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
	public PageInfo<Notice> getNoticeList(String category, Pageable pageable) {
		int rowCnt = mentoringMapper.getNoticeListCount(category);
		pageable.setRowPerPage(20);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("category",category);
		List<Notice> contents = mentoringMapper.getNoticeList(paramMap);
		return new PageInfo<>(contents, pageable, rowCnt);
	}

}
