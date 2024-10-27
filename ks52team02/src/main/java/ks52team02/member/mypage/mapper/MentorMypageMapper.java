package ks52team02.member.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mypage.dto.MentorInfo;

@Mapper
public interface MentorMypageMapper {

	MentorInfo getMentorInfoById(String mentorId);
	
	int modifyMentor(MentorInfo mentorInfo);
	
}
