package ks52team02.home.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ks52team02.manager.member.mapper.ManagerMemberMapper;
import ks52team02.manager.member.service.ManagerMemberService;
import ks52team02.manager.mentoring.mapper.ManagerMentoringMapper;
import ks52team02.manager.pay.mapper.ManagerPayMapper;
import ks52team02.manager.pay.service.ManagerPayService;
import ks52team02.member.honor.dto.hornorMentor;
import ks52team02.member.honor.service.MemberHonorService;
import ks52team02.member.mentoring.dto.Notice;
import ks52team02.member.mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final MemberHonorService memberHonorService; 
	private final MentoringService mentoringService;
	private final ManagerMemberMapper managerMemberMapper; 
	private final ManagerMentoringMapper managerMentoringMapper;
	private final ManagerPayMapper managerPayMapper;
	private final ManagerPayService managerPayService; 
	private final ManagerMemberService managerMemberService; 
	
	@GetMapping("/")
	public String indexMove() {
		return "index";
	}
	
	@GetMapping(value = {"/member","/member/"})
	public String MemberPageMove(Model model) {
		
		List<hornorMentor> honorMentorList = memberHonorService.getHonorMentorList();
		model.addAttribute("honorMentorList", honorMentorList);
		
		List<Notice> noticeList = mentoringService.getNoticeMainList();
		model.addAttribute("noticeList", noticeList);
		
		return "member/memberMain";
	}
	
	@GetMapping("/manager")
    public String managerPageMove(Model model) {
		
		int memberCnt = managerMemberMapper.getAllMemberCnt();
		int mentoringCnt = managerMentoringMapper.getMentoringCnt();
		int totalPaymentAmount = managerPayMapper.getTotalPayAmount();
		int flatformCalAmount = managerPayMapper.getFlatformCalAmount();
		
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
	    String formattedTotalPaymentAmount = numberFormat.format(totalPaymentAmount);
	    String formattedFlatformCalAmount = numberFormat.format(flatformCalAmount);
	    
	    List<Integer> monthlyPaymentCounts = managerPayService.getMonthlyPaymentCounts();
	    List<Integer> monthlyRegisterCounts = managerMemberService.getMonthlyRegisterCounts();
	    List<Integer> monthlyLoginCounts = managerMemberService.getMonthlyLoginCounts();
		
		model.addAttribute("memberCnt", memberCnt);
		model.addAttribute("mentoringCnt", mentoringCnt);
		model.addAttribute("totalPaymentAmount", formattedTotalPaymentAmount);
	    model.addAttribute("flatformCalAmount", formattedFlatformCalAmount);
	    model.addAttribute("monthlyPaymentCounts", monthlyPaymentCounts);
	    model.addAttribute("monthlyRegisterCounts", monthlyRegisterCounts);
	    model.addAttribute("monthlyLoginCounts", monthlyLoginCounts);
		
        return  "manager/managerMain";
    }

}
