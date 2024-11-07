package ks52team02.manager.career.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.career.dto.Certificate;
import ks52team02.manager.career.dto.Education;
import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
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
	
	@GetMapping("/work")
    public String mentorCareerWork(Pageable pageable,Model model) {
        System.out.println("멘토 근무경력 승인 페이지 이동");
        PageInfo<Work> memberList = careerService.getMemberWorkCareer(pageable);
       
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/workApprove";
    }

    @GetMapping("/project")
    public String mentorCareerProject(Pageable pageable, Model model) {
        System.out.println("멘토 기술경력 승인 페이지 이동");
        
        PageInfo<Project> memberList = careerService.getMemberProjectCareer(pageable);
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/projectApprove";
    }

    @GetMapping("/education")
    public String mentorCareerEducation(Pageable pageable, Model model) {
        System.out.println("멘토 학력 승인 페이지 이동");
        
        PageInfo<Education> memberList = careerService.getMemberEducationCareer(pageable);
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/educationApprove";
    }

    @GetMapping("/certificate")
    public String mentorCareerCertificate(Pageable pageable, Model model) {
        System.out.println("멘토 자격증 승인 페이지 이동");
        
        PageInfo<Certificate> memberList = careerService.getMemberCertificateCareer(pageable);
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/certificateApprove";
    }
	
}
