package ks52team02.manager.career.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.career.dto.Certificate;
import ks52team02.manager.career.dto.Education;
import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
import ks52team02.page.Pageable;

@Mapper
public interface CareerMapper {
	
	List<Work> getWorkCareer(Pageable pageable);
	
	int getWorkCareerCount();
	
	int checkWorkByFileNm(String mentorFileNm, String managerId);
	
	
	
	List<Project> getProjectCareer(Pageable pageable);
	
	int getProjectCareerCount();
	
	int checkProjectByFileNm(String mentorFileNm, String managerId);
	
	
	
	List<Education> getEducationCareer(Pageable pageable);
	
	int getEducationCareerCount();
	
	
	
	List<Certificate> getCertificateCareer(Pageable pageable);

	int getCertificateCareerCount();
	
	
}
