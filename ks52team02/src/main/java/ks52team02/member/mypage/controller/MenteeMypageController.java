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
import ks52team02.member.mypage.dto.MenteeCertificate;
import ks52team02.member.mypage.dto.MenteeEducation;
import ks52team02.member.mypage.dto.MenteeInfo;
import ks52team02.member.mypage.dto.MenteePortfolio;
import ks52team02.member.mypage.dto.MenteeProfile;
import ks52team02.member.mypage.mapper.MenteeMypageMapper;
import ks52team02.member.mypage.service.MenteeMypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/mentee")
@Slf4j
public class MenteeMypageController {
	
	private final MenteeMypageService menteeMypageService;
	private final MenteeMypageMapper menteeMypageMapper;
	private final FileService fileService;
	private final FileMapper fileMapper;
	
	
	
	//다운로드 버튼
		@GetMapping("/download")
		@ResponseBody
		public ResponseEntity<Object> archiveDownload(@RequestParam(value="mentorFileNm", required = false) String fileIdx,
								HttpServletRequest request,HttpServletResponse response) throws URISyntaxException{
			
			
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
	
	//계정정보 수정
	@PostMapping("/account")
	public String MoveMypageAccount(MenteeInfo menteeInfo) {
		menteeMypageService.modifyMentee(menteeInfo);
		
		return "redirect:/mypage/mentee/account";
	}
	
	//소개글 수정
	@PostMapping("/introduce")
	public String MoveMypageIntroduce(MenteeProfile menteeProfile) {
		menteeMypageService.modifyIntroduce(menteeProfile);
		return "redirect:/mypage/mentee/account";
	}
	
	@GetMapping("/account")
    public String MoveMypageAccount(HttpServletRequest request, Model model) {
        System.out.println("mypage account 페이지 이동");
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("SID");
        //로그인 된 아이디로 계정 정보 조회
        MenteeInfo menteeInfo = menteeMypageMapper.getMenteeInfoById(sessionId);
        //로그인 된 아이디로 소개글 조회
        MenteeProfile menteeProfile= menteeMypageMapper.getIntroduceById(sessionId);
        //아이디로 학력 조회
        List<MenteeEducation> menteeEducationInfo = menteeMypageMapper.getMenteeEducationById(sessionId);
        //아이디로 자격증 조회
        List<MenteeCertificate> menteeCertificateInfo = menteeMypageMapper.getMenteeCertificateById(sessionId);
        //아이디로 포트폴리오 조회
        List<MenteePortfolio> menteePortfolioInfo = menteeMypageMapper.getMenteePortfolioById(sessionId);
        
        model.addAttribute("menteeInfo", menteeInfo);
        model.addAttribute("menteeProfile", menteeProfile);
        model.addAttribute("menteeEducationInfo", menteeEducationInfo);
        model.addAttribute("menteeCertificateInfo", menteeCertificateInfo);
        model.addAttribute("menteePortfolioInfo", menteePortfolioInfo);
        
        
        return  "member/mypage/mentee/menteeMypage";
        
    }
	
	@PostMapping("/educationAdd")
	public String addEducationInfo(MenteeEducation menteeEducation) {
		menteeMypageService.addEducationInfo(menteeEducation);
		return "redirect:/mypage/mentee/account";
	}
	
	@GetMapping("/educationAdd")
    public String MoveMypageEducationAdd() {
        System.out.println("mypage Education 추가 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypageEducationAdd";
    }

	@PostMapping("/educationModify")
	public String modifyEducationInfo(MenteeEducation menteeEducation) {
		menteeMypageService.modifyEducationInfo(menteeEducation);
		return "redirect:/mypage/mentee/account";
	}
	
    @GetMapping("/educationModify")
    public String MoveMypageEducationModify(@RequestParam(name="menteeAcbgCode")String menteeAcbgCode, Model model) {
        System.out.println("mypage Education 수정 페이지 이동");
        MenteeEducation educationInfo= menteeMypageMapper.getEducationByCode(menteeAcbgCode);
        model.addAttribute("educationInfo", educationInfo);
        return  "member/mypage/mentee/careerInfo/menteeMypageEducationModify";
    }
	
    @PostMapping("/certificateAdd")
    public String addCertificateInfo(MenteeCertificate menteeCertificate) {
		menteeMypageService.addCertificateInfo(menteeCertificate);
    	return "redirect:/mypage/mentee/account";
    }
    
    @GetMapping("/certificateAdd")
    public String MoveMypageCertificateAdd(Model model) {
        System.out.println("mypage Certificate 추가 페이지 이동");
       List<CertificateName> certificateNameCode= menteeMypageMapper.getCertificateInfoCode();
       model.addAttribute("certificateNameCode", certificateNameCode);
        return  "member/mypage/mentee/careerInfo/menteeMypageCertificateAdd";
    }

    @PostMapping("/certificateModify")
    public String modifyCertificateInfo(MenteeCertificate menteeCertificate) {
    	menteeMypageService.modifyCertificateInfo(menteeCertificate);
    	return "redirect:/mypage/mentee/account";
    }
    
    @GetMapping("/certificateModify")
    public String MoveMypageCertificateModify(@RequestParam(name="menteeCtfcCode")String menteeCtfcCode, Model model) {
        System.out.println("mypage Certificate 수정 페이지 이동");
        MenteeCertificate certificateInfo = menteeMypageMapper.getCertificateCode(menteeCtfcCode);
        List<CertificateName> certificateNm = menteeMypageMapper.getCertificateInfoCode();
        
        model.addAttribute("certificateInfo", certificateInfo);
        model.addAttribute("certificateNm", certificateNm);
        
        return  "member/mypage/mentee/careerInfo/menteeMypageCertificateModify";
    }
	
    @PostMapping("/portfolioAdd")
    public String addPortfolioInfo(@RequestPart(name="files", required = false) MultipartFile multipartFile, MenteePortfolio menteePortfolio) {
    	String fileCode = fileService.addFile(multipartFile);
    	menteePortfolio.setMenteePtflFileNm(fileCode);
    	menteeMypageService.addPortfolioInfo(menteePortfolio);
    	return "redirect:/mypage/mentee/account";
    }
    
    @GetMapping("/portfolioAdd")
    public String MoveMypagePortfolioAdd() {
        System.out.println("mypage Portfolio 추가 페이지 이동");
        return  "member/mypage/mentee/careerInfo/menteeMypagePortfolioAdd";
    }

    @PostMapping("/portfolioModify")
    public String modifyPortfolioInfo(@RequestPart(name="files", required = false) MultipartFile multipartFile, MenteePortfolio menteePortfolio) {
    	if(!multipartFile.isEmpty()) { 
			String fileCodeForRemove = new String (menteePortfolio.getMenteePtflFileNm());
			String fileCode = fileService.addFile(multipartFile);
			menteePortfolio.setMenteePtflFileNm(fileCode);
			menteeMypageService.modifyPortfolioInfo(menteePortfolio);
			fileService.removeFileByCode(fileCodeForRemove);
		}else {
			menteeMypageService.modifyPortfolioInfo(menteePortfolio);
		}
    	
    	return "redirect:/mypage/mentee/account";
    }
    
    @GetMapping("/portfolioModify")
    public String MoveMypagePortfolioModify(@RequestParam(name="menteePtflCode")String menteePtflCode, Model model) {
        System.out.println("mypage Portfolio 수정 페이지 이동");
        MenteePortfolio portfolioInfo = menteeMypageMapper.getPortfolioCode(menteePtflCode);
        model.addAttribute("portfolioInfo", portfolioInfo);
        return  "member/mypage/mentee/careerInfo/menteeMypagePortfolioModify";
    }
    
    
	

    
}
