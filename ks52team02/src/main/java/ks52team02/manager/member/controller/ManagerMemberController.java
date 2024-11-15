package ks52team02.manager.member.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks52team02.files.dto.FileDto;
import ks52team02.files.mapper.FileMapper;
import ks52team02.manager.member.dto.LoginLog;
import ks52team02.manager.member.dto.Member;
import ks52team02.manager.member.dto.MentorApproval;
import ks52team02.manager.member.dto.WithdrawalMember;
import ks52team02.manager.member.service.ManagerMemberService;
import ks52team02.member.mypage.mapper.MentorMypageMapper;
import ks52team02.page.PageInfo;
import ks52team02.page.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/manager/member")
public class ManagerMemberController {

	private final ManagerMemberService managerMemberService;
	private final FileMapper fileMapper;
	

// 파일 다운로드 ▼ ---------------------------------------------------------------------------	
	
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
		
	
	
// 조회 only ▼ ---------------------------------------------------------------------------
	
	@GetMapping("/list")
    public String allMembers(Pageable pageable, Model model, String keyword) {
    	System.out.println("전체 회원 조회 페이지 이동");
    	PageInfo<Member> memberList = managerMemberService.getMemberList(pageable, keyword);
    	model.addAttribute("memberList", memberList);
    	
    	return  "manager/memberInfo/membersInfoList";
	}
	
	@GetMapping("/listIdSearch")
	public String allMembersIdSearch(@RequestParam(value = "keyword", required = false) String keyword,
	                            Pageable pageable, Model model) {
		System.out.println("전체 회원 조회 - ID 검색");
	    PageInfo<Member> memberPage = managerMemberService.getMemberList(pageable, keyword);
	    PageInfo<Member> memberList = managerMemberService.getMemberList(pageable, keyword);
	    model.addAttribute("memberList", memberPage.getContents());
	    model.addAttribute("pageInfo", memberPage);
	    model.addAttribute("memberList", memberList);
	    return "manager/memberInfo/membersInfoList";
	}
	
	@GetMapping("/withdrawalList")
    public String withdrawalMembers(Pageable pageable, Model model, String keyword) {
    	System.out.println("탈퇴 회원 조회 페이지 이동");
    	PageInfo<WithdrawalMember> withdrawalmemberList = managerMemberService.getWithdrawalMemberList(pageable, keyword);
    	model.addAttribute("withdrawalmemberList", withdrawalmemberList);
    	return  "manager/memberInfo/withdrawalMembersList";
    }
	
	@GetMapping("/withdrawalListIdSearch")
	public String withdrawalMembersIdSearch(@RequestParam(value = "keyword", required = false) String keyword,
	                            Pageable pageable, Model model) {
		System.out.println("탈퇴 회원 조회 - ID 검색");
	    PageInfo<WithdrawalMember> memberPage = managerMemberService.getWithdrawalMemberList(pageable, keyword);
	    PageInfo<WithdrawalMember> withdrawalmemberList = managerMemberService.getWithdrawalMemberList(pageable, keyword);
	    model.addAttribute("withdrawalmemberList", memberPage.getContents());
	    model.addAttribute("pageInfo", memberPage);
	    model.addAttribute("withdrawalmemberList", withdrawalmemberList);
	    return "manager/memberInfo/withdrawalMembersList";
	}
	
	@GetMapping("/dormantList")
    public String dormantMembers(Pageable pageable, Model model, String keyword) {
    	System.out.println("휴면 회원 조회 페이지 이동");
    	PageInfo<Member> dormantMemberList = managerMemberService.getDormantMemberList(pageable, keyword);
    	model.addAttribute("dormantMemberList", dormantMemberList);
        return  "manager/memberInfo/dormantMembersList";
    }
	
	@GetMapping("/dormantListIdSearch")
	public String dormantListIdSearch(@RequestParam(value = "keyword", required = false) String keyword,
	                            Pageable pageable, Model model) {
		System.out.println("휴면 회원 조회 - ID 검색");
		PageInfo<Member> memberPage = managerMemberService.getDormantMemberList(pageable, keyword);
	    PageInfo<Member> dormantMemberList = managerMemberService.getDormantMemberList(pageable, keyword);
	    model.addAttribute("dormantMemberList", memberPage.getContents());
	    model.addAttribute("pageInfo", memberPage);
	    model.addAttribute("dormantMemberList", dormantMemberList);
		
		return "manager/memberInfo/dormantMembersList";
	}
	
	@GetMapping("/loginLog")
    public String loginLog(Pageable pageable, Model model, String keyId, String keyLoginCode, String memberLevelCate, String loginLogStartDate, String loginLogEndDate) {
    	System.out.println("멤버 로그인 로그 조회 페이지 이동");
    	PageInfo<LoginLog> loginLogList = managerMemberService.getLoginLog(pageable, keyId, keyLoginCode, memberLevelCate, loginLogStartDate, loginLogEndDate);
    	model.addAttribute("loginLogList", loginLogList);
        return  "manager/memberInfo/memberLoginLogList";
    }
	
	@GetMapping("/loginLogSearch")
    public String loginLogSearch(Pageable pageable, Model model, 
    							@RequestParam(value = "keyId", required = false) String keyId, 
    							@RequestParam(value = "keyLoginCode", required = false) String keyLoginCode, 
    							@RequestParam(value = "memberLevelCate", required = false) String memberLevelCate, 
    							@RequestParam(value = "loginLogStartDate", required = false) String loginLogStartDate, 
    							@RequestParam(value = "loginLogEndDate", required = false) String loginLogEndDate) {
    	System.out.println("멤버 로그인 로그 검색");
    	PageInfo<LoginLog> memberPage = managerMemberService.getLoginLog(pageable, keyId, keyLoginCode, memberLevelCate, loginLogStartDate, loginLogEndDate);
    	PageInfo<LoginLog> loginLogList = managerMemberService.getLoginLog(pageable, keyId, keyLoginCode, memberLevelCate, loginLogStartDate, loginLogEndDate);
	    model.addAttribute("loginLogList", memberPage.getContents());
	    model.addAttribute("pageInfo", memberPage);
	    model.addAttribute("loginLogList", loginLogList);
	    model.addAttribute("memberLevelCate", memberLevelCate);
	    model.addAttribute("loginLogStartDate", loginLogStartDate);
	    model.addAttribute("loginLogEndDate", loginLogEndDate);
    	
        return  "manager/memberInfo/memberLoginLogList";
    }
	
	
	@GetMapping("/registeredMembers")
    public String getMonthMember(Pageable pageable, Model model, String keyword) {
    	System.out.println("한 달 내 신규회원 조회 페이지 이동");
    	PageInfo<Member> monthMemberList = managerMemberService.getMonthMemberList(pageable, keyword);
    	model.addAttribute("monthMemberList", monthMemberList);
    	
        return  "manager/memberInfo/registeredMembers";
    }
	
	@GetMapping("/registeredMembersIdSearch")
	public String registeredMembersIdSearch(@RequestParam(value = "keyword", required = false) String keyword,
            					Pageable pageable, Model model) {
		System.out.println("한 달 내 신규회원 조회 ID검색");
		PageInfo<Member> memberPage = managerMemberService.getMonthMemberList(pageable, keyword);
		PageInfo<Member> monthMemberList = managerMemberService.getMonthMemberList(pageable, keyword);
		model.addAttribute("monthMemberList", memberPage.getContents());
		model.addAttribute("pageInfo", memberPage);
		model.addAttribute("monthMemberList", monthMemberList);
		
		return "manager/memberInfo/registeredMembers";
	}

	
// 조회 only ▲ ---------------------------------------------------------------------------
	
	@GetMapping("/waitingForApproval")
	public String waitingForApproval(Pageable pageable, Model model, String keyword) {
		System.out.println("멘토 회원가입 승인 대기 내역 조회 페이지 이동");
		PageInfo<Member> waitingForApprovalMentorList = managerMemberService.getWaitingForApprovalMentorList(pageable, keyword);
		model.addAttribute("waitingForApprovalMentorList", waitingForApprovalMentorList);
		
		return  "manager/memberInfo/waitingForApprovalList";
	}
	
	@GetMapping("/waitingForApprovalIdSearch")
	public String waitingForApprovalIdSearch(@RequestParam(value = "keyword", required = false) String keyword,
											Pageable pageable, Model model) {
		System.out.println("멘토 회원가입 승인 대기 내역 ID검색");
		PageInfo<Member> memberPage = managerMemberService.getWaitingForApprovalMentorList(pageable, keyword);
		PageInfo<Member> waitingForApprovalMentorList = managerMemberService.getWaitingForApprovalMentorList(pageable, keyword);
		model.addAttribute("waitingForApprovalMentorList", memberPage.getContents());
		model.addAttribute("pageInfo", memberPage);
		model.addAttribute("waitingForApprovalMentorList", waitingForApprovalMentorList);
		
		return  "manager/memberInfo/waitingForApprovalList";
	}
	
	
	@PostMapping("/mentorApproval")
	@ResponseBody
	public int mentorApproval(
	    @RequestParam(name="memberId") String memberId,
	    @RequestParam(name="actionType") String actionType,
	    @RequestParam(name="mentorApprovalReason") String mentorApprovalReason,
	    MentorApproval mentorApproval, 
	    HttpSession session) {
	    System.out.println("멘토 - 회원가입 승인 및 권한 변경");

	    String mentorApprovalManager = (String) session.getAttribute("SID");
	    mentorApproval.setMentorApprovalManager(mentorApprovalManager);
	    mentorApproval.setMentorApprovalReason(mentorApprovalReason);  // DTO에 값 설정

	    int result = managerMemberService.approvalMentorLevel(mentorApproval, actionType);

	    return result;
	}
	
	
	@GetMapping("/waitingForWithdrawal")
    public String waitingForWithdrawal(Pageable pageable, Model model, String keyword) {
    	System.out.println("회원탈퇴 신청 대기 내역 페이지 이동");
    	PageInfo<WithdrawalMember> waitingForWithDrawalList = managerMemberService.getWaitingForWithDrawalList(pageable, keyword);
        model.addAttribute("waitingForWithDrawalList", waitingForWithDrawalList);
    
        return  "manager/memberInfo/waitingForWithdrawalList";
	}
	
	
	@GetMapping("/waitingForWithdrawalIdSearch")
    public String waitingForWithdrawalIdSearch(@RequestParam(value = "keyword", required = false) String keyword,
    										Pageable pageable, Model model) {
    	System.out.println("회원탈퇴 신청 대기 내역 ID검색");
    	PageInfo<WithdrawalMember> memberPage = managerMemberService.getWaitingForWithDrawalList(pageable, keyword);
    	PageInfo<WithdrawalMember> waitingForWithDrawalList = managerMemberService.getWaitingForWithDrawalList(pageable, keyword);
        model.addAttribute("waitingForWithDrawalList", memberPage.getContents());
        model.addAttribute("pageInfo", memberPage);
        model.addAttribute("waitingForWithDrawalList", waitingForWithDrawalList);
    
        return  "manager/memberInfo/waitingForWithdrawalList";
	}
	
	@PostMapping("/withdrawalApprove")
    @ResponseBody
    public boolean withdrawalApprove(WithdrawalMember withdrawalMember, HttpSession session) {
		
		System.out.println("회원탈퇴");
        boolean isapprove = false;

        if(withdrawalMember.getWithdrawalMemberId() == null) {
            return isapprove;
        }

        String withdrawalApplyManager = (String) session.getAttribute("SID");
        withdrawalMember.setWithdrawalApplyManager(withdrawalApplyManager);


        log.info("withdrawalMemberId : {}", withdrawalMember.getWithdrawalMemberId());
        log.info("withdrawalApplyManager : {}", withdrawalApplyManager);

        int applyResult = managerMemberService.withdrawalApply(withdrawalMember);

        if(applyResult > 0) {
            isapprove = true;
        }

        return isapprove;
    }
	
	
	@GetMapping("/managerWithdrawal")
    public String managerWithdrawal(HttpSession session, Model model) {
    	System.out.println("관리자 탈퇴 페이지 이동");
    	
    	String withdrawalMemberId = (String) session.getAttribute("SID");
    	
    	Member managerInfo = managerMemberService.getMemberInfoById(withdrawalMemberId);
		model.addAttribute("managerInfo", managerInfo);
    	
        return  "manager/setting/managerWithdrawal";
    }

	
	@PostMapping("/managerWithdrawal")
	@ResponseBody
	public boolean managerWithdrawal(WithdrawalMember withdrawalManager, HttpSession session) {
		
		System.out.println("관리자 탈퇴");
		boolean isapprove = false;
		
		if(withdrawalManager.getWithdrawalMemberId() == null) {
            return isapprove;
        }
		
		String withdrawalMemberId = (String) session.getAttribute("SID");
		String withdrawalMemberLevelCode = (String) session.getAttribute("SLEVEL");
		withdrawalManager.setWithdrawalMemberId(withdrawalMemberId);
		withdrawalManager.setWithdrawalMemberLevelCode(withdrawalMemberLevelCode);
		
		log.info("withdrawalMemberId : {}", withdrawalMemberId);
		
		managerMemberService.delMember(withdrawalManager);
		
		int result = managerMemberService.managerWithdrawalApply(withdrawalManager);
		
        if(result > 0) {
            isapprove = true;
            if (session != null) {
                session.invalidate(); // 세션이 null이 아닐 때만 무효화
            }
        }
        return isapprove;
	}
	
	
	@PostMapping("/infoModify")
    public String membersInfoModify(Member member) {
		System.out.println("회원 정보 수정");
		managerMemberService.updateMemberInfoById(member);
    	
        return "redirect:/manager/member/infoModify";
    }
	
	
	
	@GetMapping("/infoModify")
	public String membersInfoModify(@RequestParam(name="memberId", required=false) String memberId,
									Pageable pageable, Model model, String keyword){
		System.out.println("전체 회원 정보 수정 페이지 이동");
		PageInfo<Member> memberList = managerMemberService.getMemberList(pageable, keyword);
		model.addAttribute("memberList", memberList);
	
		if(memberId != null) {
		Member memberInfo = managerMemberService.getMemberInfoById(memberId);
		model.addAttribute("memberInfo", memberInfo);
		}
		
		return "manager/memberInfo/membersInfoModifyForm";
	}


	
	@GetMapping("/infoModifyIdSearch")
	public String getInfoModifySearchList(@RequestParam(value = "keyword", required = false) String keyword,
	                            Pageable pageable, Model model) {
		System.out.println("전체 회원 정보 수정 ID로 검색");
		PageInfo<Member> memberPage = managerMemberService.getMemberList(pageable, keyword);
		PageInfo<Member> memberList = managerMemberService.getMemberList(pageable, keyword);
		model.addAttribute("memberList", memberPage.getContents());
	    model.addAttribute("pageInfo", memberPage);
	    model.addAttribute("memberList", memberList);
	    
	    return "manager/memberInfo/membersInfoModifyForm";
		}
	
}
