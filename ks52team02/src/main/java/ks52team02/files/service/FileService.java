package ks52team02.files.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
		// 단일 파일 등록
		String addFile(MultipartFile multipartFile);
		
		// 다중 파일 등록
		void addFiles(MultipartFile[] multipartFile);
		
		// 파일 삭제
		void removeFileByCode(String fileCode);
		
}
