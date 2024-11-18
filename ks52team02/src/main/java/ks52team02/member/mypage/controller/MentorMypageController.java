package ks52team02.member.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks52team02.files.dto.FileDto;
import ks52team02.files.mapper.FileMapper;
import ks52team02.files.service.FileService;
import ks52team02.member.mypage.dto.CertificateName;
import ks52team02.member.mypage.dto.MentorCertificate;
import ks52team02.member.mypage.dto.MentorEducation;
import ks52team02.member.mypage.dto.MentorInfo;
import ks52team02.member.mypage.dto.MentorProject;
import ks52team02.member.mypage.dto.MentorWork;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import ks52team02.member.mypage.service.MentorMypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/mentor")
@Slf4j
public class MentorMypageController {

	private final MentorMypageMapper mentorMypageMapper;
	private final MentorMypageService mentorMypageService;
	private final FileService fileService;
	private final FileMapper fileMapper;
	
	
	//다운로드 버튼
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<Object> archiveDownload(@RequestParam(value="mentorFileNm", required = false) String fileIdx,
							HttpServletRequest request,HttpServletResponse response) throws URISyntaxException{
		
		
		if(fileIdx != null) {
			FileDto fileDto = fileMapper.getFileInfoByCode(fileIdx);
			
			File file = new File("/home/teamproject/teachtalk" + fileDto.getFilePath());
		
			Path path = Paths.get(file.getAbsolutePath());
	        Resource resource;
			try {
				resource = new UrlResource(path.toUri());
				String contentType = null;
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				if(contentType == null) {
					contentType = "application/octet-stream";
				}
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(contentType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileDto.getFileNm(),"UTF-8").replaceAll("\\+", "%20") + "\";")
						.body(resource);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		URI redirectUri = new URI("/");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);
		
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	
	//계정정보 수정
	 @PostMapping("/account")
	 public String MoveMypageAccount(MentorInfo mentorInfo) {
		 
		 mentorMypageService.modifyMentor(mentorInfo);
		 
		 log.info("mentorInfo ;{}",mentorInfo);
		 
		 return "redirect:/mypage/mentor/account";
	 }
	
	//계정정보 조회
	@GetMapping("/account")
    public String MoveMypageAccount(HttpServletRequest request, Model model) {
		
        System.out.println("mypage account 페이지 이동");
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("SID");
		//멘토 계정 정보 아이디로 조회
		MentorInfo mentorInfo = mentorMypageMapper.getMentorInfoById(sessionId);
		log.info("mentorInfo:{}", mentorInfo);
		//멘토 근무 경력 아이디로 조회
		List<MentorWork> mentorWorkInfo = mentorMypageMapper.getMentorWorkById(sessionId);
		//프로젝트 경력 아이디로 조회
		List<MentorProject> mentorProjectInfo = mentorMypageMapper.getMentorProjectById(sessionId);
		//학력 아이디로 조회 
		List<MentorEducation> mentorEducationInfo = mentorMypageMapper.getMentorEducationById(sessionId);
		//자격증 아이디로 조회
		List<MentorCertificate> mentorCertificateInfo = mentorMypageMapper.getMentorCertificateById(sessionId);
		
		
		model.addAttribute("mentorInfo", mentorInfo);
		model.addAttribute("mentorWorkInfo", mentorWorkInfo);
		model.addAttribute("mentorProjectInfo", mentorProjectInfo);
		model.addAttribute("mentorEducationInfo", mentorEducationInfo);
		model.addAttribute("mentorCertificateInfo", mentorCertificateInfo);
		 
        return  "member/mypage/mentor/mentorMypage";
    }
    
	@PostMapping("/workAdd")
	public String AddWorkInfo(@RequestPart(name="files", required = false) MultipartFile multipartFile, MentorWork mentorWork) {
		log.info("multipartFile : {}", multipartFile);
		log.info("mentorWork : {}",  mentorWork);
		String fileCode = fileService.addFile(multipartFile);
		mentorWork.setMentorFileNm(fileCode);
		mentorMypageService.addWorkInfo(mentorWork);
		return "redirect:/mypage/mentor/account";
	}
	
    @GetMapping("/workAdd")
    public String MoveMypageWorkAdd() {
        System.out.println("mypage Work 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageWorkAdd";
    }
    
    @PostMapping("/workModify")
    public String ModifyWorkInfo(@RequestPart(name="files", required = false) MultipartFile multipartFile, MentorWork mentorWork) {
    	
		if(!multipartFile.isEmpty()) { 
			String fileCodeForRemove = new String (mentorWork.getMentorFileNm());
			String fileCode = fileService.addFile(multipartFile);
			mentorWork.setMentorFileNm(fileCode);
			mentorMypageService.modifyWorkInfo(mentorWork);
			fileService.removeFileByCode(fileCodeForRemove);
		}else {
			mentorMypageService.modifyWorkInfo(mentorWork);
		}
		
    	
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/workModify")
    public String MoveMypageWorkModify(@RequestParam(name="mentorWorkCode") String mentorWorkCode, Model model) {
        System.out.println("mypage Work 수정 페이지 이동");
        MentorWork workInfo = mentorMypageMapper.getMentorWorkByCode(mentorWorkCode);
        model.addAttribute("workInfo", workInfo);
        return  "member/mypage/mentor/careerInfo/mentorMypageWorkModify";
    }
    
    @GetMapping("/workRemove")
    public String workRemove(@RequestParam(name="mentorFileNm")String mentorFileNm,
    						@RequestParam(name="mentorWorkCode")String mentorWorkCode) {
    	mentorMypageMapper.removeWorkByCode(mentorWorkCode);
    	fileService.removeFileByCode(mentorFileNm);
        return  "redirect:/mypage/mentor/account";
    }
    
    
    @PostMapping("/projectAdd")
    public String AddProjectInfo(@RequestPart(name="files", required = false)MultipartFile multipartFile, MentorProject mentorProject) {
    	String fileCode = fileService.addFile(multipartFile);
    	mentorProject.setMentorFileNm(fileCode);
    	mentorMypageService.addProjectInfo(mentorProject);
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/projectAdd")
    public String MoveMypageProjectAdd() {
        System.out.println("mypage Project 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageProjectAdd";
    }
    
    @PostMapping("/projectModify")
    public String ModifyProjectInfo(@RequestPart(name="files", required = false)MultipartFile multipartFile, MentorProject mentorProject) {
    	if(!multipartFile.isEmpty()) { 
			String fileCodeForRemove = new String (mentorProject.getMentorFileNm());
			String fileCode = fileService.addFile(multipartFile);
			mentorProject.setMentorFileNm(fileCode);
			mentorMypageService.modifyProjectInfo(mentorProject);
			fileService.removeFileByCode(fileCodeForRemove);
		}else {
			mentorMypageService.modifyProjectInfo(mentorProject);
		}
    	
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/projectModify")
    public String MoveMypageProjectModify(@RequestParam(name="mentorProjectCode") String mentorProjectCode, Model model) {
        System.out.println("mypage Project 수정 페이지 이동");
        MentorProject projectInfo = mentorMypageMapper.getMentorProjectByCode(mentorProjectCode);
        model.addAttribute("projectInfo",projectInfo);
        return  "member/mypage/mentor/careerInfo/mentorMypageProjectModify";
    }
    
    @GetMapping("/projectRemove")
    public String projectRemove(@RequestParam(name="mentorFileNm")String mentorFileNm,
    						@RequestParam(name="mentorProjectCode")String mentorProjectCode) {
    	mentorMypageMapper.removeProjectByCode(mentorProjectCode);
    	fileService.removeFileByCode(mentorFileNm);
        return  "redirect:/mypage/mentor/account";
    }
    
    @PostMapping("/educationAdd")
    public String AddEducationInfo(@RequestPart(name="files", required = false)MultipartFile multipartFile, MentorEducation mentorEducation) {
    	String fileCode = fileService.addFile(multipartFile);
    	mentorEducation.setMentorFileNm(fileCode);
    	mentorMypageService.addEducationInfo(mentorEducation);
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/educationAdd")
    public String MoveMypageEducationAdd() {
        System.out.println("mypage Education 추가 페이지 이동");
        return  "member/mypage/mentor/careerInfo/mentorMypageEducationAdd";
    }
    
    @PostMapping("/educationModify")
    public String ModifyEducationInfo(@RequestPart(name="files", required = false)MultipartFile multipartFile, MentorEducation mentorEducation) {
    	if(!multipartFile.isEmpty()) { 
			String fileCodeForRemove = new String (mentorEducation.getMentorFileNm());
			String fileCode = fileService.addFile(multipartFile);
			mentorEducation.setMentorFileNm(fileCode);
			mentorMypageService.modifyEducationInfo(mentorEducation);
			fileService.removeFileByCode(fileCodeForRemove);
		}else {
			mentorMypageService.modifyEducationInfo(mentorEducation);
		}
    	
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/educationModify")
    public String MoveMypageEducationModify(@RequestParam(name="mentorEducationCode") String mentorEducationCode, Model model) {
        System.out.println("mypage Education 수정 페이지 이동");
        MentorEducation educationInfo= mentorMypageMapper.getMentorEducationByCode(mentorEducationCode);
        model.addAttribute("educationInfo", educationInfo);
        return  "member/mypage/mentor/careerInfo/mentorMypageEducationModify";
    }
    
    @GetMapping("/educationRemove")
    public String educationRemove(@RequestParam(name="mentorFileNm")String mentorFileNm,
    						@RequestParam(name="mentorEducationCode")String mentorEducationCode) {
    	mentorMypageMapper.removeEducationByCode(mentorEducationCode);
    	fileService.removeFileByCode(mentorFileNm);
        return  "redirect:/mypage/mentor/account";
    }
    
    @PostMapping("/certificateAdd")
    public String AddCertificateInfo(@RequestPart(name="files", required = false)MultipartFile multipartFile, MentorCertificate mentorCertificate) {
    	String fileCode = fileService.addFile(multipartFile);
    	mentorCertificate.setMentorFileNm(fileCode);
    	mentorMypageService.addCertificateInfo(mentorCertificate);
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/certificateAdd")
    public String MoveMypageCertificateAdd(Model model) {
        System.out.println("mypage Certificate 추가 페이지 이동");
        List<CertificateName> certificateInfoCode = mentorMypageMapper.getCertificateInfoCode();
        model.addAttribute("certificateInfoCode", certificateInfoCode);
        
        return  "member/mypage/mentor/careerInfo/mentorMypageCertificateAdd";
    }
    
    @PostMapping("/certificateModify")
    public String ModifyCertificateInfo(@RequestPart(name="files", required = false)MultipartFile multipartFile, MentorCertificate mentorCertificate) {
    	if(!multipartFile.isEmpty()) { 
			String fileCodeForRemove = new String (mentorCertificate.getMentorFileNm());
			String fileCode = fileService.addFile(multipartFile);
			mentorCertificate.setMentorFileNm(fileCode);
			mentorMypageService.modifyCertificateInfo(mentorCertificate);
			fileService.removeFileByCode(fileCodeForRemove);
		}else {
			mentorMypageService.modifyCertificateInfo(mentorCertificate);
		}
    	
    	return "redirect:/mypage/mentor/account";
    }
    
    @GetMapping("/certificateModify")
    public String MoveMypageCertificateModify(@RequestParam(name="mentorCertificateCode")String mentorCertificateCode, Model model) {
        System.out.println("mypage Certificate 수정 페이지 이동");
        List<CertificateName> certificateInfoNmCode = mentorMypageMapper.getCertificateInfoCode();
        MentorCertificate certificateInfoCode = mentorMypageMapper.getMentorCertificateByCode(mentorCertificateCode);
        
        model.addAttribute("certificateInfoNmCode", certificateInfoNmCode);
        model.addAttribute("certificateInfoCode", certificateInfoCode);
        
        return  "member/mypage/mentor/careerInfo/mentorMypageCertificateModify";
    }
    
    @GetMapping("/certificateRemove")
    public String certificateRemove(@RequestParam(name="mentorFileNm")String mentorFileNm,
    						@RequestParam(name="mentorCertificateCode")String mentorCertificateCode) {
    	mentorMypageMapper.removeCertificateByCode(mentorCertificateCode);
    	fileService.removeFileByCode(mentorFileNm);
        return  "redirect:/mypage/mentor/account";
    }
    
}
