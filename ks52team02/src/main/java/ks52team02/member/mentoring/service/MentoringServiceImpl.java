package ks52team02.member.mentoring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.mapper.MentoringMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MentoringServiceImpl implements MentoringService{
	
	private final MentoringMapper mentoringMapper;
	
	@Override
		public List<Notice> getNoticeList() {
			
			List<Notice> noticeList = mentoringMapper.getNoticeList();
			
			return noticeList;
		}

}
