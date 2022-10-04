package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Session;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Member;

@Controller
public class UsrHomeController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public Object showMain(HttpSession httpSession) {
		
		return "안녕하세요";
	}
	
}
