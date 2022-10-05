package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.vo.Member;

@Controller
public class UsrHomeController {
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public Object showMain(HttpSession httpSession) {
	    
	Member member = SessionController.loginMember;
		
		return member;
	}
	
}
