package ks52team02.manager.career.service;

import java.util.List;

import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;

public interface CareerService {

	List<Work> getMemberWorkCareer();
	
	List<Project> getMemberProjectCareer();
	
	
	
}
