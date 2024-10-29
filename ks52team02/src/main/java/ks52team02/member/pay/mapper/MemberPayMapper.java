package ks52team02.member.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.pay.dto.Pay;

@Mapper
public interface MemberPayMapper {
	
	// 진행

	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
	
	// 결제 내역
	List<Pay> getMenteePaymentListById(String memberId);
}
