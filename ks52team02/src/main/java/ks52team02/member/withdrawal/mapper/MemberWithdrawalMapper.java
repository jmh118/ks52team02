package ks52team02.member.withdrawal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import ks52team02.manager.member.dto.WithdrawalMember;

@Mapper
public interface MemberWithdrawalMapper {
	
	// 탈퇴 요청
	int memberWithdrawal(WithdrawalMember withdrawalMember);
}
