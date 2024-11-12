package ks52team02.member.register.service;

import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.member.mypage.dto.MentorWork;

public interface MemberRegisterService {
	
	// 회원가입
	void register(Member member);
	
	// 멘토 회원가입 요청
	int mentorPreRegister(MentorApproval mentorApproval);

}
