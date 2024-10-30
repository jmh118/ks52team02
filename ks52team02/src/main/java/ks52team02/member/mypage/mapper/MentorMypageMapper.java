package ks52team02.member.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mypage.dto.MentorEducation;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorProject;
import ks52team02.member.mypage.dto.MentorWork;

@Mapper
public interface MentorMypageMapper {
	//계정정보 조회
	MentorInfo getMentorInfoById(String mentorId);
	//계정정보 수정
	int modifyMentor(MentorInfo mentorInfo);
	//근무경력 조회
	List<MentorWork> getMentorWorkById(String mentorId);
	//프로젝트경력 조회
	List<MentorProject> getMentorProjectById(String mentorId);
	//학력 조회
	List<MentorEducation> getMentorEducationById(String mentorId);
	
	
}
