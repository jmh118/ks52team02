package ks52team02.member.portfolio.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks52team02.files.dto.FileDto;
import ks52team02.files.mapper.FileMapper;
import ks52team02.member.portfolio.dto.Portfolio;
import ks52team02.member.portfolio.mapper.PortfolioMapper;
import ks52team02.member.portfolio.service.PortfolioService;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/portfolio")
@Slf4j
public class MemberPortfolioController {
	
	private final PortfolioService portfolioService;
	private final PortfolioMapper portfolioMapper;
	private final FileMapper fileMapper;
	
	
	//다운로드 버튼
	@GetMapping("/download")
	@ResponseBody
	public ResponseEntity<Object> archiveDownload(@RequestParam(value="portfolioFile", required = false) String fileIdx,
							HttpServletRequest request,HttpServletResponse response) throws URISyntaxException{
		log.info("fileIdx : {}", fileIdx);
		
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
	
	@GetMapping("/list")
    public String MovePortfolio(Pageable pageable, Model model) {
    	System.out.println("포트폴리오 | 멘티의 포트폴리오 전체 조회 화면");
    	
    	PageInfo<Portfolio> PortfolioList= portfolioService.getPortfolioList(pageable);
    	model.addAttribute("PortfolioList", PortfolioList);
    	
        return  "member/portfolio/allMenteePortfolioList";
    }
	
	@GetMapping("/detail")
    public String MovePortfolioDetail(@RequestParam(name="portfolioCode") String portfolioCode, Model model) {
    	System.out.println("포트폴리오 상세 조회 화면");
    	Portfolio portfolioDetail = portfolioMapper.getPortfolioDetailByCode(portfolioCode);
    	log.info("portfolioDetail : {}", portfolioDetail);
    	
    	model.addAttribute("portfolioDetail", portfolioDetail);
    	
        return  "member/portfolio/menteePortfolioDetail";
    }
	
	
}
