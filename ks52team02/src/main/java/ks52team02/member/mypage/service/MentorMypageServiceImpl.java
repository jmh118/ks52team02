package ks52team02.member.mypage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.member.mypage.dto.MentorCertificate;
import ks52team02.member.mypage.dto.MentorEducation;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorProject;
import ks52team02.member.mypage.dto.MentorWork;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MentorMypageServiceImpl implements MentorMypageService{

	
	private final MentorMypageMapper mentorMypageMapper;
	private final CommonMapper commonMapper;
	
	
	@Override
	public void modifyMentor(MentorInfo mentorInfo) {
		mentorMypageMapper.modifyMentor(mentorInfo);
	}
	
	
	@Override
	public void addWorkInfo(MentorWork mentorWork) {
		String nextCode = commonMapper.getPrimaryKey("mentor_work_history_details", "mentor_work_career_details_code", "mentor_work_career_details_code_");
		mentorWork.setMentorWorkCode(nextCode);			//테이블명, 자동증가 시킬 컬럼 명, 자동증가 시킬 컬럼 명_
		mentorMypageMapper.addWorkInfo(mentorWork);
	}
	
	
	@Override
	public void addProjectInfo(MentorProject mentorProject) {
		String nextCode1 = commonMapper.getPrimaryKey("mentor_tech_career_detail", "mentor_TECH_career_detail_code", "mentor_TECH_career_detail_code_");
		String nextCode2 = commonMapper.getPrimaryKey("mentor_tech_career_detail", "mentor_work_career_details_code", "mentor_work_career_details_code_");
		mentorProject.setMentorProjectCode(nextCode1);
		mentorProject.setMentorWorkCode(nextCode2);
		mentorMypageMapper.addProjectInfo(mentorProject);
	}
	
	
	@Override
	public void addEducationInfo(MentorEducation mentorEducation) {
		String nextCode = commonMapper.getPrimaryKey("mentor_acbg_detail", "mentor_ACBG_detail_code", "mentor_ACBG_detail_code_");
		mentorEducation.setMentorEducationCode(nextCode);
		mentorMypageMapper.addEducationInfo(mentorEducation);
	}
	
	
	@Override
	public void addCertificateInfo(MentorCertificate mentorCertificate) {
		String nextCode = commonMapper.getPrimaryKey("mentor_certificate_detail", "mentor_certificate_detail_code", "mentor_certificate_detail_code_");
		mentorCertificate.setMentorCertificateCode(nextCode);
		mentorMypageMapper.addCertificateInfo(mentorCertificate);
	}
	
}
