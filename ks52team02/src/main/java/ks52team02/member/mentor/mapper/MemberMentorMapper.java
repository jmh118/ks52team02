package ks52team02.member.mentor.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ks52team02.manager.member.dto.Member;

@Mapper
public interface MemberMentorMapper {
	
	// 멘토 조회
	List<Member> getMentorList();
	
	// 명예멘토 조회
	List<Member> getHonorMentorList();
}
