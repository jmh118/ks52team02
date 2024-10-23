package ks52team02.member.mentor.service;

import java.util.List;
import ks52team02.manager.member.dto.Member;

public interface MemberMentorService {

	// 멘토 조회
	List<Member> getMentorList();
	
	// 명예멘토 조회
	List<Member> getHonorMentorList();
		
}
