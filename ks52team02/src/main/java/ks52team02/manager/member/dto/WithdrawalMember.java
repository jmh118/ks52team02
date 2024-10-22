package ks52team02.manager.member.dto;

import lombok.Data;

@Data
public class WithdrawalMember {
	private String withdrawalMemberCode;
	private String withdrawalMemberId;
	private String withdrawalMemberLevelCode;
	private String withdrawalReason;
	private String withdrawalDemandYMD;
	private String withdrawalApprovalYMD;
	private String withdrawalApprovalYN;
	private String withdrawalYMD;
	private String withdrawalApplyManager;
	
	private String withdrawalMemberPw;
	private String withdrawalMemberLevel;
}
