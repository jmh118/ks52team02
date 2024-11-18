package ks52team02.member.mentor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import ks52team02.manager.member.dto.Member;

@Mapper
public interface MemberMentorMapper {
	
	// 멘토 조회
	// 멘토 권한 멤버수 조회
	List<Member> getMentorList(Map<String, Object> paramMap);
	int getMentorListCount(String keyId);
	
	// 명예멘토 조회
	// 명예멘토 권한 멤버수 조회
	List<Member> getHonorMentorList(Map<String, Object> paramMap);
	int getHonorMentorListCount(String keyId);
}
