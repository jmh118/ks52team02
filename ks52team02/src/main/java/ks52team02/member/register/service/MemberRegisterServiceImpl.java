package ks52team02.member.register.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import groovy.util.logging.Log;
import ks52team02.common.mapper.CommonMapper;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.member.mypage.dto.MentorWork;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import ks52team02.member.register.mapper.MemberRegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberRegisterServiceImpl implements MemberRegisterService {

	private final MemberRegisterMapper memberRegisterMapper;
	private final CommonMapper commonMapper;
	
	
	@Override
	public void register(@RequestParam Member member) {
		int res = memberRegisterMapper.register(member);
	}

	@Override
	public int mentorPreRegister(MentorApproval mentorApproval) {
		String mentorPreRegisterNextCode = commonMapper.getPrimaryKey("mentor_authority_approval", "mentor_authority_approval_code", "mentor_authority_approval_code_");
		mentorApproval.setMentorApprovalCode(mentorPreRegisterNextCode);
		log.info("fff : {}", mentorPreRegisterNextCode);
		
		
		return memberRegisterMapper.mentorPreRegister(mentorApproval);
	}


}
