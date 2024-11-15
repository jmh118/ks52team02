package ks52team02.member.mentor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.member.dto.Member;
import ks52team02.member.mentor.mapper.MemberMentorMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberMentorServiceImpl implements MemberMentorService {
	
	private final MemberMentorMapper memberMentorMapper;
	
	@Override
	public PageInfo<Member> getMentorList(Pageable pageable, String keyId) {
		int rowCnt = memberMentorMapper.getMentorListCount(keyId);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		pageable.setRowPerPage(12);
		paramMap.put("rowPerPage", pageable.getRowPerPage());
		paramMap.put("offset", pageable.getOffset());
		paramMap.put("keyId", keyId);
		
		List<Member> contents = memberMentorMapper.getMentorList(paramMap);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}

	@Override
	public List<Member> getHonorMentorList() {
		List<Member> honorMentorList = memberMentorMapper.getHonorMentorList();
		
		return honorMentorList;
	}

	
	
}
