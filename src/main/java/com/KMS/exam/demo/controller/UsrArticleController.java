package com.KMS.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	int articleId = 0;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	class Article {
		private int id;
		private String title;
		private String body;
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articleId+1;
		Article article = new Article(id, title, body);

		List<Article> articles = new ArrayList<>();
		articles.add(article);
		
		return article;
	}
	
}
