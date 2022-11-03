package com.KMS.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Ut;

import lombok.Getter;
@Component
@Scope(value = "request", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	@Autowired
	public MemberService memberservice;
	
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");

		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		
		this.req.setAttribute("rq", this);
	}



	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		println(Ut.jsHistoryBack(msg));
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void println(String str) {
		print(str + "\n");
	}
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	public boolean isNotLogined() {
		return !isLogined;
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
	public String jsHistoryBackOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		return "usr/common/js";
	}



	public void initOnBeforeActionInterceptor() {
		
	}

}
