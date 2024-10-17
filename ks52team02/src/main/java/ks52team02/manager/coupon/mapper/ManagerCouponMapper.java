package ks52team02.manager.coupon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.coupon.dto.MemberCoupon;



@Mapper
public interface ManagerCouponMapper {

	// 관리자 멘티 쿠폰 보유 내역 조회
	List<MemberCoupon> getMenteeCouponList();
}
