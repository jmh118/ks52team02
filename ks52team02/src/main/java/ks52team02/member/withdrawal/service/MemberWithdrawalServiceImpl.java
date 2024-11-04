package ks52team02.member.withdrawal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.member.withdrawal.mapper.MemberWithdrawalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class MemberWithdrawalServiceImpl implements MemberWithdrawalService {
	
	private final MemberWithdrawalMapper memberWithdrawalMapper;
	private final CommonMapper withdrawalCommonMapper;

	@Override
	public int memberWithdrawal(WithdrawalMember withdrawalMember) {
		String withdrawalNextCode = withdrawalCommonMapper.getPrimaryKey("member_withdrawal_apply", "withdrawal_apply_member_code", "withdrawal_apply_member_code_");
		withdrawalMember.setWithdrawalMemberCode(withdrawalNextCode);
		
		return memberWithdrawalMapper.memberWithdrawal(withdrawalMember);
	}



}
