package ks52team02.member.mypage.service;

import ks52team02.member.mypage.dto.MenteeEducation;
import ks52team02.member.mypage.dto.MenteeInfo;
import ks52team02.member.mypage.dto.MenteeProfile;

public interface MenteeMypageService {

	void modifyMentee(MenteeInfo menteeInfo);
	
	void modifyIntroduce(MenteeProfile menteeProfile);
	
	void addEducationInfo(MenteeEducation menteeEducation);
}
