package ks52team02.member.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.mentoring.dto.Notice;
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
	public String getNextNoticeCode() {
		
		return mentoringMapper.getNextNoticeCode();
	}
	
	@Override
	public void addNotice(Notice notice) {
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
