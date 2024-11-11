package ks52team02.manager.career.service;

import java.util.List;

import ks52team02.manager.career.dto.Certificate;
import ks52team02.manager.career.dto.Education;
import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;

public interface CareerService {

	PageInfo<Work> getMemberWorkCareer(Pageable pageable);
	PageInfo<Work> getMemberWorkCareer(Pageable pageable, String selectedFilter);
	
	PageInfo<Project> getMemberProjectCareer(Pageable pageable);
	
	PageInfo<Education> getMemberEducationCareer(Pageable pageable);
	
	PageInfo<Certificate> getMemberCertificateCareer(Pageable pageable);

	
	
	
	
}
