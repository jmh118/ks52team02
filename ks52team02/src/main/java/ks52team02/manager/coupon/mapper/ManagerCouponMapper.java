package ks52team02.manager.coupon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.coupon.dto.CouponCriteriaList;
import ks52team02.manager.coupon.dto.MemberCoupon;



@Mapper
public interface ManagerCouponMapper {
	
	// 관리자 목표 진행 누적 개수에 의해 쿠폰 지급 가능 내역 조회
	List<CouponCriteriaList> getMenteeCouponCriteriaList();

	// 관리자 멘티 쿠폰 보유 내역 조회
	List<MemberCoupon> getMenteeCouponList();
}
