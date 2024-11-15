package ks52team02.member.register.mapper;

import org.apache.ibatis.annotations.Mapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.member.mypage.dto.MentorWork;

@Mapper
public interface MemberRegisterMapper {
	
	// 회원가입
	int register(Member member);

	// 멘토 회원가입 요청
	int mentorPreRegister(MentorApproval mentorApproval);

	// 아이디 중복체크
	boolean dupicatedCheckById(String memberId);

	// 멘토 회원가입시 근무경력 등록
	void registerAddWorkInfo(MentorWork mentorWork);



}
