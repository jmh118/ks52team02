package ks52team02.member.mypage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.member.mypage.dto.MenteeCertificate;
import ks52team02.member.mypage.dto.MenteeEducation;
import ks52team02.member.mypage.dto.MenteeInfo;
import ks52team02.member.mypage.dto.MenteePortfolio;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.member.mypage.mapper.MenteeMypageMapper;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MenteeMypageServiceImpl implements MenteeMypageService {

	private final MenteeMypageMapper menteeMypageMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public void modifyMentee(MenteeInfo menteeInfo) {
		menteeMypageMapper.modifyMentee(menteeInfo);
	}
	
	@Override
	public void modifyIntroduce(MenteeProfile menteeProfile) {
		menteeMypageMapper.modifyIntroduce(menteeProfile);
	}
	
	@Override
	public void addEducationInfo(MenteeEducation menteeEducation) {
		String nextCode = commonMapper.getPrimaryKey("mentee_acbg_details", "mentee_ACBG_details_code", "mentee_ACBG_details_code_");
		menteeEducation.setMenteeAcbgCode(nextCode);			//테이블명, 자동증가 시킬 컬럼 명, 자동증가 시킬 컬럼 명_
		menteeMypageMapper.addEducationInfo(menteeEducation);
	}

	@Override
	public void addCertificateInfo(MenteeCertificate menteeCertificate) {
		String nextCode = commonMapper.getPrimaryKey("mentee_certificate_details", "mentee_certificate_details_code", "mentee_certificate_details_code_");
		menteeCertificate.setMenteeCtfcCode(nextCode);
		menteeMypageMapper.addCertificateInfo(menteeCertificate);
	}
	
	@Override
	public void addPortfolioInfo(MenteePortfolio menteePortfolio) {
		String nextCode = commonMapper.getPrimaryKey("mentee_portfolio_registration_details", "mentee_portfolio_registration_details_code", "mentee_portfolio_registration_details_code_");
		menteePortfolio.setMenteePtflCode(nextCode);
		menteeMypageMapper.addPortfolioInfo(menteePortfolio);
	}
	
	@Override
	public void modifyEducationInfo(MenteeEducation menteeEducation) {
		menteeMypageMapper.modifyEducationInfo(menteeEducation);
	}
	
	@Override
	public void modifyCertificateInfo(MenteeCertificate menteeCertificate) {
		menteeMypageMapper.modifyCertificateInfo(menteeCertificate);
		
	}
	
	@Override
	public void modifyPortfolioInfo(MenteePortfolio menteePortfolio) {
		menteeMypageMapper.modifyPortfolioInfo(menteePortfolio);
		
	}
	
	
}
