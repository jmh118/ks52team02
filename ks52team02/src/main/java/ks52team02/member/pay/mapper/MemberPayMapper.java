package ks52team02.member.pay.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.pay.dto.PaymentSettlement;
import ks52team02.member.pay.dto.BeforePay;
import ks52team02.member.pay.dto.MentoringData;
import ks52team02.member.pay.dto.Pay;

@Mapper
public interface MemberPayMapper {
	
	// (멘토) 정산 내역 - 연도, 월 검색로 조회
	List<PaymentSettlement> searchSettlementHistoryList(Map<String, Object> filterParams);

	// (멘토) 정산 내역 행 개수 조회
	int getSettlementHistoryListCnt(String memberId);
	
	// (멘토) 정산 내역 조회
	List<PaymentSettlement> getSettlementHistoryList(Map<String, Object> params);

	// 연도,월 검색 결제된 조회
	List<Pay> getFilterMentorPaymentListById(Map<String, Object> filterParams);
	
	// 연도,월 검색 결제한 내역 조회
	List<Pay> getFilterMenteePaymentListById(Map<String, Object> filterParams);
	
	// 멘토링 신청 삭제 이후 공고 상태 수정
	int updateMentoringDetailStatusByCode(String detailCode);
	
	// 멘토링 신청 삭제 
	int removeMentoringApplyByCode(String applyCode);
	
	// 멘토링 모집 공고 상세 상태 변경
	int updateMentroingDatailStatus(List<String> detailCode);
	
	// 멘토링 신청 엡데이트 이후 공고 상세 상태 변경을 위한 공고 상세 코드 조회
	List<String> getNoticeDetailCodeByApplyCode(List<String> applyCode);
	
	// 결제 추가 성공 이후 멘토링 신청 상태 업데이트 
	int updateMentoringApplyStarus(List<String> applyCode);
	
	// 결제 추가 
	int addPay(MentoringData mentoringData);
	
	// 멘토링 미결제건 내역 조회
	int getBeforePayCnt(String memberId);
	
	// 결제 전 멘토링 신청 목록 조회
	List<BeforePay> getBeforePayListById(String memberId);
	
	// 정산 신청 
	int addSettlementApply(Map<String, Object> params);
	
	// 정산신청을 했는지 조회
	int getSettlementCntByPayCode(String payCode);
	
	// (멘토) 신청받은 결제 내역 행 개수 조회
	int getPaymentListCntByMentorId(String memberId);
	
	// (멘토) 신청받은 결제 내역 조회 
	List<Pay> getPaymentListByMentorId(Map<String, Object> params);

	// 결제한 멘토링명 조회
	String getMentoringTitleByPayCode(String payCode);
	
	// 결제 내역 행 조회
	int getPaymentListCnt(String memberId);
	
	// (멘티) 결제 내역
	List<Pay> getMenteePaymentListById(Map<String, Object> params);
}
