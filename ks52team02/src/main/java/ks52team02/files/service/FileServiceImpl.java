package ks52team02.files.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ks52team02.files.dto.FileDto;
import ks52team02.files.mapper.FileMapper;
import ks52team02.files.util.FilesUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService{

	private final FilesUtils filesUtils;
	private final FileMapper fileMapper;
	
	@Override
	public void addFile(MultipartFile multipartFile) {
		FileDto fileInfo = filesUtils.uploadFile(multipartFile);
		if(fileInfo != null) fileMapper.addFile(fileInfo);
	}
	
	
}
