package ks52team02.manager.member.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.WithdrawalMember;

@Mapper
public interface ManagerMemberMapper {

	// 관리자 - 전체 회원 조회
	List<Member> getMemberList();
	
	// 관리자 - 탈퇴 회원 조회	
	List<WithdrawalMember> getWithdrawalMemberList();
	
	// 관리자 - 휴면 회원 정보 조회
	List<Member> getDormantMemberList();
	
}
