package ks52team02.manager.career.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;

@Mapper
public interface CareerMapper {
	
	List<Work> getWorkCareer();
	
	List<Project> getProjectCareer();
	

}
