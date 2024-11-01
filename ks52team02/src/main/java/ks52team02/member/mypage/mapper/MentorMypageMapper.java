package ks52team02.member.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mypage.dto.CertificateName;
import ks52team02.member.mypage.dto.MentorCertificate;
import ks52team02.member.mypage.dto.MentorEducation;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorProject;
import ks52team02.member.mypage.dto.MentorWork;

@Mapper
public interface MentorMypageMapper {
	//계정정보 조회
	MentorInfo getMentorInfoById(String mentorId);
	//계정정보 수정
	int modifyMentor(MentorInfo mentorInfo);
	//근무경력 조회
	List<MentorWork> getMentorWorkById(String mentorId);
	//코드로 근무경력 조회
	MentorWork getMentorWorkByCode(String mentorWorkCode);
	//코드로 근무경력 수정
	int modifyWorkInfo(MentorWork mentorWork);
	//프로젝트경력 조회
	List<MentorProject> getMentorProjectById(String mentorId);
	//코드로 프로젝트경력 조회
	MentorProject getMentorProjectByCode(String mentorProjectCode);
	//코드로 프로젝트경력 수정
	int modifyProjectInfo(MentorProject mentorProject);
	//학력 조회
	List<MentorEducation> getMentorEducationById(String mentorId);
	//코드로 학력 조회
	MentorEducation getMentorEducationByCode(String mentorEducationCode);
	//코드로 학력 수정
	int modifyEducationInfo(MentorEducation mentorEducation);
	//자격증 조회
	List<MentorCertificate> getMentorCertificateById(String mentorId);
	//코드로 자격증 조회
	MentorCertificate getMentorCertificateByCode(String mentorCertificateCode);
	//코드로 자격증 수정
	int modifyCertificateInfo(MentorCertificate mentorCertificate);
	//근무경력 등록
	int addWorkInfo(MentorWork mentorWork);
	//프로젝트 경력 등록
	int addProjectInfo(MentorProject mentorProject);
	//학력 등록
	int addEducationInfo(MentorEducation mentorEducation);
	//자격증 등록
	int addCertificateInfo(MentorCertificate mentorCertificate);
	//자격증의 이름,발급기관 체크박스
	List<CertificateName> getCertificateInfoCode();
	
	
	
	
}
