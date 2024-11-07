package ks52team02.manager.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import ks52team02.common.mapper.CommonMapper;
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
	
	 
	@Override
	public PageInfo<Member> getMemberList(Pageable pageable) {
		int rowCnt = managerMemberMapper.getMemberListCount();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		// paramMap.put("category",category); <- 검색기능 추가
		List<Member> contents = managerMemberMapper.getMemberList(paramMap);
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public PageInfo<WithdrawalMember> getWithdrawalMemberList(Pageable pageable) {
		int rowCnt = managerMemberMapper.getWithdrawalMemberListCount();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		List<WithdrawalMember> contents = managerMemberMapper.getWithdrawalMemberList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public PageInfo<Member> getDormantMemberList(Pageable pageable) {
		int rowCnt  = managerMemberMapper.getDormantMemberListCount();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		List<Member> contents = managerMemberMapper.getDormantMemberList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<LoginLog> getLoginLog(Pageable pageable){
		int rowCnt = managerMemberMapper.getLoginLogCount();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		List<LoginLog> contents = managerMemberMapper.getLoginLog(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public List<Member> getMentorList() {
		List<Member> mentorList = managerMemberMapper.getMentorList();
		
		return mentorList;
	}

	@Override
	public List<Member> getMonthMemberList() {
		List<Member> monthMemberList = managerMemberMapper.getMonthMemberList();

		return monthMemberList;
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

