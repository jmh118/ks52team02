package ks52team02.member.login.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberLoginInterceptor implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		boolean isProcess = true;	
		
		if(sessionId == null) {
			response.sendRedirect("/");
			isProcess = false;
		}else {
			String sessionLevel = (String) session.getAttribute("SLEVEL");
			String requestUri = request.getRequestURI();
			
			if("member_level_mentee".equals(sessionLevel) || "member_level_mentor".equals(sessionLevel)) {
				if(requestUri.indexOf("/manager") > -1) {
					response.sendRedirect("/member/managerLogin");
					isProcess = false;
				}
			}
			
		}
		
		return isProcess;
	}
	

}
