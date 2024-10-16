package ks52team02.manager.goal.dto;

import lombok.Data;

@Data
public class MemberGoal {
	
	private String goalCode;
	private String memberId;
	private String topicCateCode;
	private String goalName;
	private String goalContent;
	private String goalBeginningDate;
	private String goalEndDate;
	private String goalStatus;
	private String goatAddDate;
	private char disclosireStatus;

}
