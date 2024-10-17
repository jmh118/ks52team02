package ks52team02.member.register.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ks52team02.manager.member.dto.Member;
import ks52team02.member.register.mapper.MemberRegisterMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRegisterServiceImpl implements MemberRegisterService {

	private final MemberRegisterMapper memberMapper;
	
	// 멘티 회원가입
	@Override
	public void register(@RequestParam Member member) {
		int res = memberMapper.register(member);
	}

}
