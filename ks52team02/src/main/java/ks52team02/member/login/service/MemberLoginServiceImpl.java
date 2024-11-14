package ks52team02.member.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks52team02.common.mapper.CommonMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.member.login.mapper.MemberLoginMapper;
import ks52team02.member.withdrawal.dto.WithdrawalStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class MemberLoginServiceImpl implements MemberLoginService {

	private final MemberLoginMapper memberLoginMapper;
	private final CommonMapper commonMapper;
	
	@Override
	public String loginProcess(String memberId, String memberPw, HttpSession session, RedirectAttributes reAttr) {
	    String viewName;
	    String msg = "회원의 정보가 일치하지 않습니다. 다시 로그인해주세요~";

	    Map<String, Object> loginMap = checkedMember(memberId, memberPw);
	    boolean checkMember = (boolean) loginMap.get("isCheck");

	    if (checkMember) {
	        if (!memberWithdrawalStatus(memberId)) {
	            viewName = "redirect:/member/login";
	            if (isCheckMemberLevel(memberId)) {
	                viewName = "redirect:/member/managerLogin";
	            }
	            msg = "탈퇴한 회원이거나 탈퇴 신청 중인 회원은 로그인이 불가합니다.";
	            reAttr.addAttribute("msg", msg);
	            return viewName;
	        }

	        Member memberInfo = (Member) loginMap.get("memberInfo");
	        String memberLevel = memberInfo.getMemberLevel();

	        if ("member_level_manager".equals(memberLevel)) {
	            viewName = "redirect:/manager";
	        } else {
	            viewName = "redirect:/member";
	        }

	        session.setAttribute("SID", memberId);
	        session.setAttribute("SLEVEL", memberLevel);

	    } else {
	        String level = memberLoginMapper.getMemberLevelById(memberId);
	        if (level == null) {
	            viewName = "redirect:/member/login";
	            msg = "존재하지 않는 회원입니다.";
	        } else if ("member_level_manager".equals(level)) {
	            viewName = "redirect:/member/managerLogin";
	        } else {
	            viewName = "redirect:/member/login";
	        }
	        reAttr.addAttribute("msg", msg);
	    }

	    return viewName;
	}
	
	
	@Override
	public boolean memberWithdrawalStatus(String memberId) {

		WithdrawalStatus status = memberLoginMapper.getMemberWithdrawalStatus(memberId);
		
		if(status.getIsWithdrawal() == 'Y' || status.getWithdrawalApplyCnt() > 0) {
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public boolean isCheckMemberLevel(String memberId) {
		
		boolean isManager = false;
		
		Member memberInfo = memberLoginMapper.getMemberInfoById(memberId);
		if(memberInfo.getMemberLevel().equals("member_level_manager")) {
			isManager = true;
		}
		
		return isManager;
	}
	
	
	@Override
	public boolean isCheckMemberPw(String memberId, String memberPw) {
		
		boolean ischeckPw = false;
		Member memberInfo = memberLoginMapper.getMemberInfoById(memberId);
		
		if(memberInfo != null) {
			String checkPw = memberInfo.getMemberPw();
			if(checkPw.equals(memberPw)) {
				ischeckPw = true;
				
			}
		}
		
		return ischeckPw;
	}

	@Override
	public Map<String, Object> checkedMember(String memberId, String memberPw) {
		
		boolean isCheck = false;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Member memberInfo = memberLoginMapper.getMemberInfoById(memberId);
		
		if(memberInfo != null) {
			String checkPw = memberInfo.getMemberPw();
			if(checkPw.equals(memberPw)) {
				isCheck = true;
				resultMap.put("memberInfo", memberInfo);
				
				String loginCode = commonMapper.getPrimaryKey("member_login_log", "login_log_code", "login_log_code_");
				String memberLevelCode = memberInfo.getMemberLevel();
				
				memberLoginMapper.addLoginLog(loginCode, memberId, memberLevelCode);
			}
		}
		
		resultMap.put("isCheck", isCheck);
		
		return resultMap;
		
	}

	@Override
	public String findMemberPwById(String inputId) {
		String foundPw = memberLoginMapper.findMemberPwById(inputId);

		return foundPw;
	}




}
