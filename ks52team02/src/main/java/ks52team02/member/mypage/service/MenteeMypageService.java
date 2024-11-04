package ks52team02.member.mypage.service;

import ks52team02.member.mypage.dto.MenteeCertificate;
import ks52team02.member.mypage.dto.MenteeEducation;
import ks52team02.member.mypage.dto.MenteeInfo;
import ks52team02.member.mypage.dto.MenteePortfolio;
import ks52team02.member.mypage.dto.MenteeProfile;

public interface MenteeMypageService {

	void modifyMentee(MenteeInfo menteeInfo);
	
	void modifyIntroduce(MenteeProfile menteeProfile);
	
	void addEducationInfo(MenteeEducation menteeEducation);
	
	void addCertificateInfo(MenteeCertificate menteeCertificate);
	
	void addPortfolioInfo(MenteePortfolio menteePortfolio);
	
	void modifyEducationInfo(MenteeEducation menteeEducation);
	
	void modifyCertificateInfo(MenteeCertificate menteeCertificate);
	
	void modifyPortfolioInfo(MenteePortfolio menteePortfolio);
}
