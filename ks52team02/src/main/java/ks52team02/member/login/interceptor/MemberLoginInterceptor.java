package ks52team02.member.login.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberLoginInterceptor implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		boolean isProcess = true;	
		
		if(sessionId == null) {
			log.info("세션이 없습니다. 요청 URI: {}", request.getRequestURI());
			response.sendRedirect("/");
			isProcess = false;
		}else {
			log.info("세션 확인됨, sessionId : {}", sessionId);
			String sessionLevel = (String) session.getAttribute("SLEVEL");
			String requestUri = request.getRequestURI();
			if("member_level_mentee".equals(sessionLevel) || "member_level_mentor".equals(sessionLevel)) {
				if(requestUri.indexOf("/manager") > -1) {
					log.info("멘티나 멘토는 관리자 페이지에 접근할 수 없습니다. 요청 URI: {}", requestUri);
					response.sendRedirect("/member/managerLogin");
					isProcess = false;
				}
			}
			
		}
		
		return isProcess;
	}
	

}
