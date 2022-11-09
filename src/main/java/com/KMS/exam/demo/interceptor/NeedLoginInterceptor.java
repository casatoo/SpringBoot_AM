package com.KMS.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Rq;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private Rq rq;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		String afterUrl = req.getRequestURI();
		String params = req.getQueryString();
		
		afterUrl += "?"+params;
		if (!rq.isLogined()) {
//			resp.getWriter().append("<script>")...

			rq.printjsReplace("로그인 후 이용해주세요",Ut.f("../member/login?afterUrl=%s", afterUrl));
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
