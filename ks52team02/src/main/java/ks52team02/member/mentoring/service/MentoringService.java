package ks52team02.member.mentoring.service;

import java.util.List;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeList;
import ks52team02.member.mentoring.dto.Topic;

public interface MentoringService {
	// 공고 코드 추가
	String getNextNoticeCode();
	
	//공고등록
	void addNotice(Notice notice);
	
	// 공고목록조회
	List<NoticeList> getNoticeList();
	
	// 공고카테고리조회
	List<Topic> getTopicList();

}
