package ks52team02.manager.goal.dto;

import lombok.Data;

@Data
public class MemberGoalRecord {
	
	private String goalRecordCode;
	private String goalCode;
	private String memberId;
	private String goalAddDate;
	private String goalRecordTitle;
	private String goalRecordContent;
	private String goalRecordBeginningTime;
	private String goalRecordEndTime;
	private String goalRecordHour;
	private String goalRecordACMLHour;
	private String goalRecordACMLCnt;
	private String goalRecordAddDate;
	
	
	private MemberGoal goal;    
	private GoalTopic topic;
}
