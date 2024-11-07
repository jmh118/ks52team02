package ks52team02.manager.member.dto;

import lombok.Data;

@Data
public class Member {
	
	private String memberId; 
	private String memberPw; 
	private String memberPwAnswer; 
	private String memberLevel; 
	private String memberName; 
	private String memberTelNo; 
	private String memberEmail; 
	private String memberJoinYMD; 
	private String memberWithdrawalYN; 
	
	private String newMemberId; 
	private String newMemberPw; 
	private String newMemberPwAnswer; 
	private String newMemberLevel; 
	private String newMemberName; 
	private String newMemberTelNo; 
	private String newMemberEmail; 
	private String newMemberJoinYMD; 
	private String newMemberWithdrawalYN;
	
	private String dormantDays;
	private String isHonorMentor;
	

}
