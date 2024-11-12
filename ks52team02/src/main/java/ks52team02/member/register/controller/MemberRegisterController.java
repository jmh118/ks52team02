package ks52team02.member.register.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks52team02.files.dto.FileDto;
import ks52team02.files.mapper.FileMapper;
import ks52team02.files.service.FileService;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.member.mypage.dto.MentorWork;
import ks52team02.member.register.dto.MentorRegisterData;
import ks52team02.member.register.mapper.MemberRegisterMapper;
import ks52team02.member.register.service.MemberRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/register")
public class MemberRegisterController {
	
	private final MemberRegisterMapper memberRegisterMapper;
	private final MemberRegisterService memberRegisterService;
	
	private final FileMapper fileMapper;
	private final FileService fileService;
	
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
	
	
	
	@GetMapping("/info")
	public String moveJoinInfo() {
		System.out.println("회원가입 | 회원가입 안내 화면");
		return  "member/register/registerInfo";
	}
	
	@GetMapping("/mentee")
    public String registerMentee(Model model) {
		model.addAttribute("title", "회원가입");
    	System.out.println("멘티 회원가입 화면");
    	
        return  "member/register/registerMenteeForm";
    }
	
	@PostMapping("/mentee")
	public String registerMentee(Member member) {
		memberRegisterMapper.register(member);
		System.out.println("멘티 회원가입");
		
		return  "redirect:/member";
	}

	@GetMapping("/mentor1")
    public String registerMentor1(Model model) {
		model.addAttribute("title", "회원가입");
    	System.out.println("멘토 회원가입 화면1");
        return  "member/register/registerMentor1Form";
    }
	
	@PostMapping("/mentor1")
    public String registerMentor1(Member member) {
		memberRegisterService.register(member);
    	System.out.println("멘토 - 멘티 권한으로 회원가입");
    	return "redirect:/register/mentor2";
	}
	
	@GetMapping("/mentor2")
    public String registerMentor2() {
    	System.out.println("멘토 회원가입 화면2");
    	
        return  "member/register/registerMentor2Form";
    }
	

	  @PostMapping("/mentor")
	  public String registerMentor(@RequestPart(name="registerFiles", required = false) 
	  								MultipartFile multipartFile, 
	  								MentorApproval mentorApproval, 
	  								@RequestParam(name="hiddenMemberId") String memberId) { 
		  System.out.println("멘토 회원가입");

		  mentorApproval.setMemberId(memberId);
		  fileService.addFile(multipartFile);
		  memberRegisterService.mentorPreRegister(mentorApproval);
		  
		  return "redirect:/member"; 
	  }
	
	
	@PostMapping("/dupicatedCheckById")
	@ResponseBody
	public boolean duplicatedIdCheck(@RequestParam String memberId) {
		log.info("memberId : {}", memberId);
		boolean isDuplicate = false;
		isDuplicate = memberRegisterMapper.dupicatedCheckById(memberId);
		
		return isDuplicate;
	}

}
