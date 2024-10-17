package ks52team02.manager.career.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.career.dto.Education;
import ks52team02.manager.career.dto.Project;
import ks52team02.manager.career.dto.Work;
import ks52team02.manager.career.service.CareerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/career")
@Slf4j
public class ManagerCareerController {

	private final CareerService careerService;
	
	@GetMapping("/work")
    public String mentorCareerWork(Model model) {
        System.out.println("멘토 근무경력 승인 페이지 이동");
        
        List<Work> memberList = careerService.getMemberWorkCareer();
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/workApprove";
    }

    @GetMapping("/project")
    public String mentorCareerProject(Model model) {
        System.out.println("멘토 기술경력 승인 페이지 이동");
        
        List<Project> memberList = careerService.getMemberProjectCareer();
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/projectApprove";
    }

    @GetMapping("/education")
    public String mentorCareerEducation(Model model) {
        System.out.println("멘토 학력 승인 페이지 이동");
        
        List<Education> memberList = careerService.getMemberEducationCareer();
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/educationApprove";
    }

    @GetMapping("/certificate")
    public String mentorCareerCertificate() {
        System.out.println("멘토 자격증 승인 페이지 이동");
        return  "manager/career/certificateApprove";
    }
	
}
