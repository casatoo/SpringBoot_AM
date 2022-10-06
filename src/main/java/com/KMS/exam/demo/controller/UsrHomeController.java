package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.KMS.exam.demo.vo.Member;

@Controller
public class UsrHomeController {
	
	@RequestMapping("/usr/home/main")
	public Object showMain(HttpSession httpSession, Model model) {
			Member loginMember = SessionController.loginMember;
	    	model.addAttribute("loginMember",loginMember);
		return "/usr/home/main";
	}
	
}
