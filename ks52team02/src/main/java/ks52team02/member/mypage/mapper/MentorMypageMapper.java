package ks52team02.member.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mypage.dto.MentorInfo;

@Mapper
public interface MentorMypageMapper {
	//계정정보 조회
	MentorInfo getMentorInfoById(String mentorId);
	//계정정보 수정
	int modifyMentor(MentorInfo mentorInfo);
	
	//근무경력 조회
	
	
	
}
