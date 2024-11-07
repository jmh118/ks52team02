package ks52team02.member.pay.dto;

import lombok.Data;

@Data
public class MentoringData {
	
	private String payCode;
	private String memberId;
	private String applyCode;
    private String noticeCode;
    private String isHonorMentor;
    private int fee;
    
    private int amount;

}
