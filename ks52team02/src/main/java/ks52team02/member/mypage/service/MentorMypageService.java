package ks52team02.member.mypage.service;

import ks52team02.member.mypage.dto.MentorCertificate;
import ks52team02.member.mypage.dto.MentorEducation;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorProject;
import ks52team02.member.mypage.dto.MentorWork;

public interface MentorMypageService {

	void modifyMentor(MentorInfo mentorInfo);

	void addWorkInfo(MentorWork mentorWork);
	
	void modifyWorkInfo(MentorWork mentorWork);
	
	void addProjectInfo(MentorProject mentorProject);
	
	void modifyProjectInfo(MentorProject mentorProject);
	
	void addEducationInfo(MentorEducation mentorEducation);
	
	void modifyEducationInfo(MentorEducation mentorEducation);
	
	void addCertificateInfo(MentorCertificate mentorCertificate);

	void modifyCertificateInfo(MentorCertificate mentorCertificate);

	
	
	
}
