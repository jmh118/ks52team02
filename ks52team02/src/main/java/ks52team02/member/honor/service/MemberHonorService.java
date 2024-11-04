package ks52team02.member.honor.service;

import java.util.List;

import ks52team02.member.honor.dto.hornorMentor;

public interface MemberHonorService {
	
	// 사용자 메인 화면 명예멘토 조회
	List<hornorMentor> getHonorMentorList();

}
