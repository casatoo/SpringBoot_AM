package com.KMS.exam.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.KMS.exam.demo.vo.Member;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}

	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
}
