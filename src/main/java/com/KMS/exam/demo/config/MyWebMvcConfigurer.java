package com.KMS.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.KMS.exam.demo.interceptor.BeforeActionInterceptor;
import com.KMS.exam.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	// BeforeActionInterceptor 불러오기
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	// NeedLoginInterceptor 불러오기
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	// /resource/common.css
	// 인터셉터 적용
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration ir;
		
		ir = registry.addInterceptor(beforeActionInterceptor)
			.addPathPatterns("/**")
			.addPathPatterns("/favicon.ico")
			.excludePathPatterns("/resource/**")
			.excludePathPatterns("/error");
		
		ir = registry.addInterceptor(needLoginInterceptor)
			.addPathPatterns("/usr/article/doAdd")
			.addPathPatterns("/usr/article/write")
			.addPathPatterns("/usr/article/modify")
			.addPathPatterns("/usr/article/doModify")
			.addPathPatterns("/usr/article/doDelete")
			.addPathPatterns("/usr/member/doLogout")
			.addPathPatterns("/usr/member/info")
			.addPathPatterns("/usr/reaction/doReaction")
			.addPathPatterns("/usr/comment/doAdd")
			.addPathPatterns("/usr/comment/doDelete")
			.addPathPatterns("/usr/comment/doModify");
	}

}
