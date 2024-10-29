package ks52team02.member.pay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.member.pay.mapper.MemberPayMapper;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberPayServiceImpl implements MemberPayService {

	private final MemberPayMapper memberPayMapper;
	
	@Override
	public String getMentoringTitleByPayCode(String payCode) {
		
		String titleName = memberPayMapper.getMentoringTitleByPayCode(payCode);
				
		return titleName;
	}
}
