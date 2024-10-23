package ks52team02.member.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.member.dto.Member;

@Mapper
public interface MemberLoginMapper {
	
	// 관리자 로그인 화면에서 관리자 아이디 검증을 위한 권한 조회
	String getMemberLevelById(String inputId);
	
	// 회원 여부 확인
	Member getMemberInfoById(String inputId);

}
