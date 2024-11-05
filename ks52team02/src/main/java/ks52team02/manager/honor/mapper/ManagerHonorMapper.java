package ks52team02.manager.honor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.honor.dto.CriteriaHonorMentor;
import ks52team02.manager.member.dto.Member;


@Mapper
public interface ManagerHonorMapper {
	
	// 명예멘토 여부 확인
	String gethonorMentorCheck(String memberId);
	
	// 명예멘토 취소 
	int honorMentorCancel(String memeberId);
	
	// 명예멘토 승인
	int honorMentorApprove(String memeberId);
	
	// 명예멘토 기준 조회
	CriteriaHonorMentor getCriteriaHonorMentor();
	
	// 명예멘토 리스트 행 개수 조회
	int getHornorMentorListCnt(List<String> mentorId);
	
	// 명예멘토 리스트 조회
	List<Member> getHornorMentorList(Map<String, Object> params);
	
	// 명예멘토인 멘토 아이디 리스트 조회
	List<String> getHonorMentorIdList();

}
