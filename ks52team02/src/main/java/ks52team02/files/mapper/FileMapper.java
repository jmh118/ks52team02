package ks52team02.files.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks52team02.files.dto.FileDto;

@Mapper
public interface FileMapper {
	//파일 업로드
	int addFile(FileDto fileInfo);
	//다중 파일 업로드
	int addFiles(List<FileDto> fileList);
	//파일 코드로 파일 다운로드
	FileDto getFileInfoByCode(String fileCode);
	//파일 코드로 파일 삭제
	int removeFileByCode(String fileCode);
}
