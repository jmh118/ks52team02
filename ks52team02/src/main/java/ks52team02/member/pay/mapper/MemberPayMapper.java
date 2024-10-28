package ks52team02.member.pay.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.pay.dto.MemberPay;

@Mapper
public interface MemberPayMapper {

	// 결제 내역
	List<MemberPay> getMenteePaymentListById(String memberId);
}
