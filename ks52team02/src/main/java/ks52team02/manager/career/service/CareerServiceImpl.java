package ks52team02.manager.career.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.career.dto.Career;
import ks52team02.manager.career.mapper.WorkMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CareerServiceImpl implements CareerService {

	private final WorkMapper workMapper;
	
	@Override
	public List<Career> getMemberCareer() {
		
		List<Career> memberList = workMapper.getMemberCareer();
		
		return memberList;
	}
	
}
