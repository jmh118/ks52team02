package ks52team02.member.mentoring.service;

import java.util.List;

import ks52team02.member.mentoring.dto.NoticeList;
import ks52team02.member.mentoring.dto.Topic;

public interface MentoringService {
	
	// 공고목록조회
	List<NoticeList> getNoticeList();
	
	// 공고카테고리조회
	List<Topic> getTopicList();

}
