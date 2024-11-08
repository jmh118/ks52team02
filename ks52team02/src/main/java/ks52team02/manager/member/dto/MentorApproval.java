package ks52team02.manager.member.dto;

import lombok.Data;

@Data
public class MentorApproval {
	
	private String mentorApprovalCode;
	private String memberId;
	private String mentorApprovalOrRejectYMD;
	private String mentorApprovalStatus;
	private String mentorApprovalManager;
	private String hornorMentorYN;
	private String mentorApprovalReason;
	
	private String actionType;
}
