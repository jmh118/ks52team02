package ks52team02.manager.career.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks52team02.manager.career.dto.Career;
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
        
        List<Career> memberList = careerService.getMemberCareer();
        
        model.addAttribute("memberList", memberList);
        log.info("memberList :{}", memberList);
        
        return  "manager/career/workApprove";
    }

    @GetMapping("/project")
    public String mentorCareerProject() {
        System.out.println("멘토 기술경력 승인 페이지 이동");
        return  "manager/career/projectApprove";
    }

    @GetMapping("/education")
    public String mentorCareerEducation() {
        System.out.println("멘토 학력 승인 페이지 이동");
        return  "manager/career/educationApprove";
    }

    @GetMapping("/certificate")
    public String mentorCareerCertificate() {
        System.out.println("멘토 자격증 승인 페이지 이동");
        return  "manager/career/certificateApprove";
    }
	
}
