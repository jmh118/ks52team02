package ks52team02.manager.career.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.manager.career.dto.Career;

@Mapper
public interface WorkMapper {
	
	List<Career> getMemberCareer();

}
