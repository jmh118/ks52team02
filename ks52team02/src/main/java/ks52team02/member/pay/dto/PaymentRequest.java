package ks52team02.member.pay.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentRequest {

	private List<MentoringData> mentoringData; 
    private int totalAmount; 
    private String impUid; 
}
