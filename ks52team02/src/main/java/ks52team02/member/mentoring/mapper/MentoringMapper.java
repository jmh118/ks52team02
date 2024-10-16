package ks52team02.member.mentoring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mentoring.dto.Notice;

@Mapper
public interface MentoringMapper {
	
	//공고조회
	List<Notice> getNoticeList();

}
