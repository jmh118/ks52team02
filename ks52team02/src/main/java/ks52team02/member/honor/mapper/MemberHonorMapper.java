package ks52team02.member.honor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.honor.dto.hornorMentor;

@Mapper
public interface MemberHonorMapper {

	// 사용자 메인 화면 명예멘토 조회
	List<hornorMentor> getHonorMentorList(List<String> mentorId);
}
