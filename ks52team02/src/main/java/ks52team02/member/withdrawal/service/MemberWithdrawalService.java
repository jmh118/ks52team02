package ks52team02.member.withdrawal.service;

import ks52team02.manager.member.dto.WithdrawalMember;

public interface MemberWithdrawalService {

	// 탈퇴 요청
	int memberWithdrawal(WithdrawalMember withdrawalMember);


}
