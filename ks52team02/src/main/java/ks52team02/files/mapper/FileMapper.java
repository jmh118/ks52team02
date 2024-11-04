package ks52team02.files.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.files.dto.FileDto;

@Mapper
public interface FileMapper {

	int addFile(FileDto fileInfo);
	
}
