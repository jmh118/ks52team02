package ks52team02.manager.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerMemberServiceImpl implements ManagerMapperService {

	private final MemberMapper memberMapper;

	@Override
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		
		return memberList;
	}
}
