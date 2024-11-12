package ks52team02.member.mentor.service;

import java.util.List;

import ks52team02.manager.member.dto.Member;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface MemberMentorService {

	// 멘토 조회
	PageInfo<Member> getMentorList(Pageable pageable);
	
	// 명예멘토 조회
	List<Member> getHonorMentorList();

		
}
