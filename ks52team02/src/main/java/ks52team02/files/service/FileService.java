package ks52team02.files.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	// 단일 파일 등록
		void addFile(MultipartFile multipartFile);
}
