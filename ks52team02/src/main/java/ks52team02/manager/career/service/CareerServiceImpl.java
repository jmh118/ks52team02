package ks52team02.manager.career.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
import ks52team02.manager.career.mapper.CareerMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CareerServiceImpl implements CareerService {

	private final CareerMapper careerMapper;
	

	
	@Override
	public List<Work> getMemberWorkCareer() {
		
		List<Work> memberList = careerMapper.getWorkCareer();
		
		return memberList;
	}
	
	@Override
	public List<Project> getMemberProjectCareer() {
		
		List<Project> memberList = careerMapper.getProjectCareer();
		
		return memberList;
	}
	
}
