package ks52team02.member.mypage.service;

import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorWork;

public interface MentorMypageService {

	void modifyMentor(MentorInfo mentorInfo);

	void addWorkInfo(MentorWork mentorWork);
	
}
