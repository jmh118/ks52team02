package ks52team02.member.mentor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentor.mapper.MemberMentorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberMentorServiceImpl implements MemberMentorService {
	
	private final MemberMentorMapper memberMentorMapper;
	
	@Override
	public List<Member> getMentorList() {
		List<Member> mentorList = memberMentorMapper.getMentorList();
		
		return mentorList;
	}

	@Override
	public List<Member> getHonorMentorList() {
		List<Member> honorMentorList = memberMentorMapper.getHonorMentorList();
		
		return honorMentorList;
	}
	
	
}
