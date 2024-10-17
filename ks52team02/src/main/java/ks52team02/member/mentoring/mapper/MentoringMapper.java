package ks52team02.member.mentoring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.dto.NoticeList;
import ks52team02.member.mentoring.dto.Topic;

@Mapper
public interface MentoringMapper {
	// 공고 코드 추가
	String getNextNoticeCode();
	
	// 공고등록
	int addNotice(Notice notice);
	
	//공고조회
	List<NoticeList> getNoticeList();
	
	// 공고카테고리조회
	List<Topic> getTopicList();

}
