package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Member;

@Controller
public class UsrHomeController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public int showMain(HttpServletRequest request) {
		
		
		int loginedId = Ut.getLoginedId(request);
		Member member = memberService.getMember(loginedId);
		int loginedLevel = member.getAuthLevel();
	
		return loginedLevel;
	}
	
}
