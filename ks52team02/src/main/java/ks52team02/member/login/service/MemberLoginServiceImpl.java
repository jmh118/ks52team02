package ks52team02.member.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.login.mapper.MemberLoginMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class MemberLoginServiceImpl implements MemberLoginService {

	private final MemberLoginMapper memberLoginMapper;
	
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
				
			}
		}
		
		resultMap.put("isCheck", isCheck);
		
		return resultMap;
		
	}




}
