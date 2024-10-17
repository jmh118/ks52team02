package ks52team02.manager.member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ks52team02.manager.member.dto.Member;

@Mapper
public interface ManagerMemberMapper {

	// 관리자 - 전체 회원 조회
	List<Member> getMemberList();
}
