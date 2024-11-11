package ks52team02.manager.career.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ks52team02.manager.career.dto.Certificate;
import ks52team02.manager.career.dto.Education;
import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
import ks52team02.manager.career.mapper.CareerMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CareerServiceImpl implements CareerService {

	private final CareerMapper careerMapper;
	

	
	@Override
	public PageInfo<Work> getMemberWorkCareer(Pageable pageable) {
		int rowCnt = careerMapper.getWorkCareerCount();
		List<Work> contents = careerMapper.getWorkCareer(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<Project> getMemberProjectCareer(Pageable pageable) {
		int rowCnt = careerMapper.getProjectCareerCount();
		List<Project> contents = careerMapper.getProjectCareer(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<Education> getMemberEducationCareer(Pageable pageable) {
		int rowCnt = careerMapper.getEducationCareerCount();
		List<Education> contents = careerMapper.getEducationCareer(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<Certificate> getMemberCertificateCareer(Pageable pageable) {
		int rowCnt = careerMapper.getCertificateCareerCount();
		List<Certificate> contents = careerMapper.getCertificateCareer(pageable);
		
		return new PageInfo<>(contents, pageable, rowCnt);
	}
}
