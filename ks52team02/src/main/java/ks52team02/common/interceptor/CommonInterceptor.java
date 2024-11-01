package ks52team02.common.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks52team02.member.pay.service.MemberPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class CommonInterceptor implements HandlerInterceptor{
	
	private final MemberPayService memberPayService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		Set<String> paramMap = request.getParameterMap().keySet();
		
		
		StringJoiner param = new StringJoiner(", ");
		
		for(String paramKey : paramMap) {
			param.add(paramKey + " : " + request.getParameter(paramKey));
		}
		
		String ip = request.getHeader("X-Real-Ip") == null ? request.getRemoteAddr() : request.getHeader("X-Real-Ip"); 
		
		log.info("ACCESS INFO START============================================");
		log.info("PORT :::::::: {}", request.getLocalPort());
		log.info("SERVERNAME :::::::: {}", request.getServerName());
		log.info("HTTP METHOD :::::::: {}", request.getMethod());
		log.info("URL :::::::: {}", request.getRequestURI());
		log.info("CLIENT IP :::::::: {}", ip);
		log.info("PARAMETER :::::::: {}", param);
		log.info("ACCESS INFO END============================================");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		
		if(sessionId != null) {
			int cnt = memberPayService.getBeforePayCnt(sessionId);
			modelAndView.addObject("cnt", cnt);
		}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
