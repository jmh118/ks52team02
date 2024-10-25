package ks52team02.member.coupon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.member.coupon.dto.Coupon;

@Mapper
public interface MemberCouponMapper {
	
	List<Coupon> getMenteeCouponListById(String memberId);

}
