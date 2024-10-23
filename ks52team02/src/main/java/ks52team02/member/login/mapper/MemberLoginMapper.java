package ks52team02.member.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.member.dto.Member;

@Mapper
public interface MemberLoginMapper {
	
	Member getMemberInfoById(String inputId);

}
