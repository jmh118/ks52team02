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
	//프로젝트경력 조회
	List<MentorProject> getMentorProjectById(String mentorId);
	//학력 조회
	List<MentorEducation> getMentorEducationById(String mentorId);
	//자격증 조회
	List<MentorCertificate> getMentorCertificateById(String mentorId);
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
