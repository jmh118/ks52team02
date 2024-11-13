package ks52team02.manager.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.common.mapper.CommonMapper;
import ks52team02.common.util.MonthlyCountUtil;
import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.manager.member.mapper.ManagerMemberMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ManagerMemberServiceImpl implements ManagerMemberService {

	private final ManagerMemberMapper managerMemberMapper;
	private final CommonMapper withdrawalCommonMapper;
	private final MonthlyCountUtil monthlyCountUtil;
	
	@Override
	public List<Integer> getMonthlyLoginCounts() {
		List<Map<String, Object>> resultList = managerMemberMapper.getMonthlyLoginCnt();
		return monthlyCountUtil.getMonthlyCounts(resultList, "month", "loginCnt");
	}
	
	@Override
	public List<Integer> getMonthlyRegisterCounts() {
		 List<Map<String, Object>> resultList = managerMemberMapper.getMonthlyRegisterCnt();
		 return monthlyCountUtil.getMonthlyCounts(resultList, "month", "memberCnt");
	}
	 
	@Override
	public PageInfo<Member> getMemberList(Pageable pageable, String keyword) {
		int rowCnt = managerMemberMapper.getMemberListCount(keyword);
		pageable.setRowPerPage(15);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("keyword", keyword);
		List<Member> contents = managerMemberMapper.getMemberList(paramMap);
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public PageInfo<WithdrawalMember> getWithdrawalMemberList(Pageable pageable, String keyword) {
		int rowCnt = managerMemberMapper.getWithdrawalMemberListCount(keyword);
		pageable.setRowPerPage(15);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("keyword", keyword);
		List<WithdrawalMember> contents = managerMemberMapper.getWithdrawalMemberList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public PageInfo<Member> getDormantMemberList(Pageable pageable, String keyword) {
		int rowCnt  = managerMemberMapper.getDormantMemberListCount(keyword);
		pageable.setRowPerPage(15);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("keyword", keyword);
		List<Member> contents = managerMemberMapper.getDormantMemberList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<LoginLog> getLoginLog(Pageable pageable, String keyId, String keyLoginCode, String memberLevelCate, String loginLogStartDate, String loginLogEndDate){
		int rowCnt = managerMemberMapper.getLoginLogCount(keyId, keyLoginCode, memberLevelCate, loginLogStartDate, loginLogEndDate);
		pageable.setRowPerPage(15);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("keyId", keyId);
		paramMap.put("keyLoginCode", keyLoginCode);
		paramMap.put("memberLevelCate", memberLevelCate);
		paramMap.put("loginLogStartDate", loginLogStartDate);
		paramMap.put("loginLogEndDate", loginLogEndDate);
		List<LoginLog> contents = managerMemberMapper.getLoginLog(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public PageInfo<Member> getMentorList(Pageable pageable) {
		int rowCnt = managerMemberMapper.getMentorListCount();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		List<Member> contents = managerMemberMapper.getMentorList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}


	@Override
	public PageInfo<Member> getMonthMemberList(Pageable pageable) {
		int rowCnt = managerMemberMapper.getMonthMemberListCount();
		pageable.setRowPerPage(12);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		
		List<Member> contents = managerMemberMapper.getMonthMemberList(paramMap);

		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public List<WithdrawalMember> getWaitingForWithDrawalList() {
		List<WithdrawalMember> waitingForWithDrawalList = managerMemberMapper.getWaitingForWithDrawalList();
		
		return waitingForWithDrawalList;
	}

	@Override
	public void updateMemberInfoById(Member member) {
		managerMemberMapper.updateMemberInfoById(member);
	}

	@Override
	public Member getMemberInfoById(String memberId) {
		Member memberInfo = managerMemberMapper.getMemberInfoById(memberId);

		return memberInfo;
	}

	@Override
	public List<Member> getWaitingForApprovalMentorList() {
		List<Member> waitingForApprovalMentorList = managerMemberMapper.getWaitingForApprovalMentorList();
		
		return waitingForApprovalMentorList;
	}
	

	@Override
    public int withdrawalApply(WithdrawalMember withdrawalMember) {
        int delResult = 0;
        int applyResult = managerMemberMapper.withdrawalApply(withdrawalMember);

        if(applyResult > 0) {
            delResult = managerMemberMapper.delMember(withdrawalMember);
        } 
        return delResult;
    }

	@Override
	public int managerWithdrawalApply(WithdrawalMember withdrawalManager) {
		String withdrawalManagerNextCode = withdrawalCommonMapper.getPrimaryKey("member_withdrawal_apply", "withdrawal_apply_member_code", "withdrawal_apply_member_code_");
		withdrawalManager.setWithdrawalMemberCode(withdrawalManagerNextCode);
		
		int delResult = 0;
		int applyResult = managerMemberMapper.managerWithdrawalApply(withdrawalManager);
		
		if(applyResult>0) {
			delResult = managerMemberMapper.delMember(withdrawalManager);
		}
		
		return delResult;
	}

	@Override
	public int delMember(WithdrawalMember withdrawalMember) {
		
		return managerMemberMapper.delMember(withdrawalMember);
	}

	@Override
	public int approvalMentorLevel(MentorApproval mentorApproval, String actionType) {
		
		int finalRes = 0;
		
		if("approve".equals(actionType)) {
			int res = managerMemberMapper.approvalMentorLevel(mentorApproval);
			if(res>0) {
				finalRes = managerMemberMapper.changeMentorLevel(mentorApproval);
			}
		} else if ("deny".equals(actionType)) {
			finalRes = managerMemberMapper.approvalMentorLevel(mentorApproval);
	    }
		
		return finalRes;
	}

	@Override
	public int changeMentorLevel(MentorApproval mentorApproval) {
		return managerMemberMapper.changeMentorLevel(mentorApproval);
		
	}



}

