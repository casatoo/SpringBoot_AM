package com.KMS.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.service.ReactionService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Article;
import com.KMS.exam.demo.vo.ResultData;
import com.KMS.exam.demo.vo.Rq;

@Controller
public class UsrAjaxTestController {
	@RequestMapping("/usr/home/testPage")
	String showTestPage() {
		return "/usr/home/testPage";
	}
}
