package ks52team02.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ks52team02.common.interceptor.CommonInterceptor;
import ks52team02.member.login.interceptor.MemberLoginInterceptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final CommonInterceptor commonInterceptor;
	private final MemberLoginInterceptor memberloginInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		
		
		List<String> excludePath = new ArrayList<String>();
		
		excludePath.add("/favicon.ico");
		excludePath.add("/common/img/**");
		excludePath.add("/common/css/**");
		excludePath.add("/manager/css/**");
		excludePath.add("/manager/fonts/**");
		excludePath.add("/manager/imgs/**");
		excludePath.add("/manager/js/**");
		excludePath.add("/manager/sass/**");
		excludePath.add("/member/css/**");
		excludePath.add("/member/fonts/**");
		excludePath.add("/member/imgs/**");
		excludePath.add("/member/js/**");
		excludePath.add("/member/sass/**");
		excludePath.add("/error");
		excludePath.add("/error/**");

		
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(excludePath);
		
		excludePath.add("/");
		excludePath.add("/member");
		excludePath.add("/manager");
		excludePath.add("/register/**");
		excludePath.add("/member/findPassword");
		excludePath.add("/member/login");
		excludePath.add("/member/managerLogin");
		excludePath.add("/member/loginProc");
		excludePath.add("/member/logout");
		excludePath.add("/member/checkLevel");
		excludePath.add("/member/checkPw");
		
		// 로그인 없이 접근 가능한 경로 추가
		excludePath.add("/mentoring/notice");
	    excludePath.add("/mentoring/noticeDetail");
	    excludePath.add("/portfolio/**");
	    excludePath.add("/honor/mentorList");
	    excludePath.add("/mentor/list");
	    excludePath.add("/review/mentorReviewList");
	    
		
		registry.addInterceptor(memberloginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(excludePath);
		
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
