package com.KMS.exam.demo.vo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	@Getter
	private Member loginedMember;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	private Map<String, String> paramMap;
	
	
	public Rq(HttpServletRequest req, HttpServletResponse resp,MemberService memberService) {
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();
		paramMap = Ut.getParamMap(req);
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMember(loginedMemberId);
		}

		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
	}



	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		println(Ut.jsHistoryBack(msg));
	}
	
	public void printjsReplace(String msg,String loginUrl) {
		resp.setContentType("text/html; charset=UTF-8");
		println(Ut.jsReplace(msg,loginUrl));
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
	public void runA() {
		System.out.println("A 호출됨");
		runB();
	}

	public void runB() {
		System.out.println("B 호출됨");
	}
	
	public String getLoginUri() {
		return "../member/login?afterUri=" + getCurrentUri();
	}
	public String getLogoutUri() {
		return "../member/doLogout?afterUri=" + getCurrentUri();
	}

	public String getAfterLoginUri() {
		return getEncodedCurrentUri();
	}
	
	public String getEncodedCurrentUri() {

		return Ut.getUriEncoded(getCurrentUri());
	}

	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString();

		if (queryString != null && queryString.length() > 0) {
			currentUri += "?" + queryString;
		}

		return currentUri;
	}
}
