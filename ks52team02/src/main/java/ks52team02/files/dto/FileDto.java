package ks52team02.files.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDto {

	private String fileCode;
	private String fileNm;
	private String fileNewNm;
	private String filePath;
	private long fileSize;
	private String fileUpldDate;
	
	
}
