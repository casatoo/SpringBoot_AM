package com.KMS.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.KMS.exam.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	private Rq rq;

	public BeforeActionInterceptor(Rq rq) {
		this.rq = rq;
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		String afterUrl = req.getRequestURI();
		String params = req.getQueryString();
		
		afterUrl += "?id="+params;
		req.setAttribute("rq", rq);
		req.setAttribute("afterUrl", afterUrl);
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
