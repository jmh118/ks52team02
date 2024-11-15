package ks52team02.member.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.mypage.dto.CertificateName;
import ks52team02.member.mypage.dto.MenteeCertificate;
import ks52team02.member.mypage.dto.MenteeEducation;
import ks52team02.member.mypage.dto.MenteeInfo;
import ks52team02.member.mypage.dto.MenteePortfolio;
import ks52team02.member.mypage.dto.MenteeProfile;

@Mapper
public interface MenteeMypageMapper {
	//자격증 이름,발급기관 체크박스 조회
	List<CertificateName>getCertificateInfoCode();
	//아이디로 계정 정보 조회
	MenteeInfo getMenteeInfoById(String menteeId);
	//아이디로 계정 정보 수정
	int modifyMentee(MenteeInfo menteeInfo);
	//아이디로 소개글 조회
	MenteeProfile getIntroduceById(String memberId);
	//아이디로 소개글 수정
	int modifyIntroduce(MenteeProfile menteeProfile);
	//아이디로 학력 조회
	List<MenteeEducation> getMenteeEducationById(String menteeId);
	//아이디로 자격증 조회
	List<MenteeCertificate> getMenteeCertificateById(String menteeId);
	//아이디로 포트폴리오 조회
	List<MenteePortfolio> getMenteePortfolioById(String menteeId);
	//학력 등록
	int addEducationInfo(MenteeEducation menteeEducation);
	//자격증 등록
	int addCertificateInfo(MenteeCertificate menteeCertificate);
	//포트폴리오 등록
	int addPortfolioInfo(MenteePortfolio menteePortfolio);
	//코드로 학력 조회
	MenteeEducation getEducationByCode(String menteeAcbgCode);
	//코드로 학력 수정
	int modifyEducationInfo(MenteeEducation menteeEducation);
	//코드로 자격증 조회
	MenteeCertificate getCertificateCode (String menteeCtfcCode);
	//코드로 자격증 수정
	int modifyCertificateInfo(MenteeCertificate menteeCertificate);
	//코드로 포트폴리오 조회
	MenteePortfolio getPortfolioCode(String menteePtflCode);
	//코드로 포트폴리오 수정
	int modifyPortfolioInfo(MenteePortfolio menteePortfolio);
	//학력 삭제
	int removeEducationByCode(String menteeAcbgCode);
	//자격증 삭제
	int removeCertificateByCode(String menteeCtfcCode);
	//포트폴리오 삭제
	int removePortfolioByCode(String menteePtflCode);
}
