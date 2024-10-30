package ks52team02.member.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.pay.dto.Pay;

@Mapper
public interface MemberPayMapper {
	
	// 정산 신청 
	int addSettlementApply(Map<String, Object> params);
	
	// 정산신청을 했는지 조회
	int getSettlementCntByPayCode(String payCode);
	
	// (멘토) 신청받은 결제 내역 조회 
	List<Pay> getPaymentListByMentorId(String memberId);

	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
	
	// (멘티) 결제 내역
	List<Pay> getMenteePaymentListById(String memberId);
}
