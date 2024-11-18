package ks52team02.member.mentor.service;

import ks52team02.manager.member.dto.Member;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface MemberMentorService {

	// 멘토 조회
	PageInfo<Member> getMentorList(Pageable pageable, String keyId);
	
	// 명예멘토 조회
	PageInfo<Member> getHonorMentorList(Pageable pageable, String keyId);

		
}
