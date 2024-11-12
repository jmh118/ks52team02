package ks52team02.manager.career.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks52team02.files.dto.FileDto;
import ks52team02.files.mapper.FileMapper;
import ks52team02.manager.career.dto.Certificate;
import ks52team02.manager.career.dto.Education;
import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
import ks52team02.manager.career.mapper.CareerMapper;
import ks52team02.manager.career.service.CareerService;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/career")
@Slf4j
public class ManagerCareerController {

	private final CareerService careerService;
	private final FileMapper fileMapper;
	private final CareerMapper careerMapper;
	
	//다운로드 버튼
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<Object> archiveDownload(@RequestParam(value="mentorFileNm", required = false) String fileIdx,
							HttpServletRequest request,HttpServletResponse response) throws URISyntaxException{
		log.info("fileIdx : {}", fileIdx);
		
		if(fileIdx != null) {
			FileDto fileDto = fileMapper.getFileInfoByCode(fileIdx);
			
			File file = new File("/home/teamproject" + fileDto.getFilePath());
		
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
	
	@GetMapping("/work")
    public String mentorCareerWork(Pageable pageable,Model model) {
        System.out.println("멘토 근무경력 승인 페이지 이동");
        PageInfo<Work> memberList = careerService.getMemberWorkCareer(pageable);
       
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/workApprove";
    }
	
	@GetMapping("/workCheck")
    public String workCheck(@RequestParam(name="mentorCode") String mentorCode,
    						@RequestParam(name="managerId") String managerId) {
		Work workCheck = new Work();
		workCheck.setMentorCode(mentorCode);
		workCheck.setManagerId(managerId);
		careerMapper.checkWorkByFileNm(workCheck);
        
        return "redirect:/manager/career/work";
	}
	/*
	 * @GetMapping("/workYn") public String
	 * workYn(@RequestParam(value="selectedFilter") String selectedFilter, Pageable
	 * pageable,Model model) { PageInfo<Work> memberList =
	 * careerService.getMemberWorkCareer(pageable, selectedFilter);
	 * 
	 * model.addAttribute("memberList", memberList);
	 * 
	 * return "redirect:/manager/career/workYn"; }
	 */

    @GetMapping("/project")
    public String mentorCareerProject(Pageable pageable, Model model) {
        System.out.println("멘토 기술경력 승인 페이지 이동");
        
        PageInfo<Project> memberList = careerService.getMemberProjectCareer(pageable);
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/projectApprove";
    }
    
    @GetMapping("/projectCheck")
    public String projectCheck(@RequestParam(name="mentorCode") String mentorCode,
    						@RequestParam(name="managerId") String managerId) {
    	Project projectCheck = new Project();
    	projectCheck.setMentorCode(mentorCode);
    	projectCheck.setManagerId(managerId);
		careerMapper.checkProjectByFileNm(projectCheck);
        
        return "redirect:/manager/career/project";
    }

    @GetMapping("/education")
    public String mentorCareerEducation(Pageable pageable, Model model) {
        System.out.println("멘토 학력 승인 페이지 이동");
        
        PageInfo<Education> memberList = careerService.getMemberEducationCareer(pageable);
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/educationApprove";
    }

    @GetMapping("/educationCheck")
    public String educationCheck(@RequestParam(name="mentorCode") String mentorCode,
    						@RequestParam(name="managerId") String managerId) {
    	Education educationCheck = new Education();
    	educationCheck.setMentorCode(mentorCode);
    	educationCheck.setManagerId(managerId);
		careerMapper.checkEducationByFileNm(educationCheck);
        
        return "redirect:/manager/career/education";
    }
    
    @GetMapping("/certificate")
    public String mentorCareerCertificate(Pageable pageable, Model model) {
        System.out.println("멘토 자격증 승인 페이지 이동");
        
        PageInfo<Certificate> memberList = careerService.getMemberCertificateCareer(pageable);
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/certificateApprove";
    }
	
    @GetMapping("/certificateCheck")
    public String certificateCheck(@RequestParam(name="mentorCode") String mentorCode,
    								@RequestParam(name="managerId") String managerId) {
    	Certificate certificateCheck = new Certificate();
    	certificateCheck.setMentorCode(mentorCode);
    	certificateCheck.setManagerId(managerId);
		careerMapper.checkCertificateByFileNm(certificateCheck);
        
        return "redirect:/manager/career/certificate";
    }
    
}
