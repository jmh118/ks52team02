package ks52team02.member.register.mapper;

import org.apache.ibatis.annotations.Mapper;
import ks52team02.manager.member.dto.Member;

@Mapper
public interface MemberRegisterMapper {
	
	// 회원가입(멘티)
	int register(Member member);

	// 아이디 중복체크
	boolean dupicatedCheckById(String memberId);

}
